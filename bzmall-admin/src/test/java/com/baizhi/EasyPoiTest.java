package com.baizhi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.hutool.core.util.StrUtil;
import com.baizhi.entity.PmsBrand;
import com.baizhi.service.PmsBrandService;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BzMallApplication.class)
public class EasyPoiTest {

    @Autowired
    private PmsBrandService pmsBrandService;

    /**
     * 导出到磁盘文件中
     */
    @Test
    public void testOutput(){
        /**
         * 准备数据
         */
        List<PmsBrand> pmsBrands = pmsBrandService.list();

        /**
         * 导出参数对象
         * 参数1 标题
         * 参数2 表名
         */
        ExportParams params = new ExportParams("所有的品牌数据","brands");

        /**
         * ExcelExportUtil 导出工具类
         * exportExcel 导出方法
         * 参数1 导出参数对象
         * 参数2 要导出数据的类对象
         * 参数3 要导出的数据
         */
        Workbook workbook = ExcelExportUtil.exportExcel(params, PmsBrand.class, pmsBrands);

        /**
         * 可以自适应两种格式 xls  xlsx
         */
        try {
            workbook.write(new FileOutputStream("E://easyBrands.xlsx"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导入
     */
    @Test
    public void testInput() throws Exception {
        FileInputStream inputStream= new FileInputStream("E://easyBrands.xlsx");
        /**
         * 导入参数对象
         * setTitleRows 声明标题占有的行数
         * setHeadRows  声明表头占有的行数
         */
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        importParams.setHeadRows(1);

        /**
         * 导入方法
         * 参数1 流
         * 参数2 类对象
         * 参数3 导入参数对象
         */
        List<PmsBrand> pmsBrands = ExcelImportUtil.importExcel(inputStream, PmsBrand.class, importParams);

        pmsBrands.forEach(System.err::println);
    }

    @Test
    public void testOutput2() throws IOException {
        /**
         * 准备数据
         */
        List<PmsBrand> list = pmsBrandService.list();

        /**
         * 导出参数对象
         * 参数1 标题
         * 参数2 表明
         */
        ExportParams params = new ExportParams("所有品牌数据", "brands");

        /**
         * ExcelExportUtil 导出工具类
         * exportExcel 导出方法
         * 参数1 导出参数对象
         * 参数2 导出对象的类对象
         * 参数3 要导出的数据
         */
        Workbook workbook = ExcelExportUtil.exportExcel(params, PmsBrand.class, list);

        workbook.write(new FileOutputStream("E://brnads.xlsx"));
    }

    @Test
    public void testInput2() throws Exception {
        FileInputStream inputStream = new FileInputStream("E://brnads.xlsx");
        /**
         * 导入参数对象
         * setTitleRows 声明标题占有的行数
         * setHeadRows  声明表头占有的行数
         */
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        importParams.setHeadRows(1);

        /**
         * ExcelImportUtil导入工具类
         * importExcel导入方法
         * 参数1 导入的文件 流
         * 参数2 导入的对象的类对象
         * 参数3 导出对象
         */
        List<PmsBrand> pmsBrands = ExcelImportUtil.importExcel(inputStream, PmsBrand.class, importParams);
        pmsBrands.forEach(System.err::println);
    }

    /**
     * 图片导出
     */
    @Test
    public void testOutputPhoto() throws IOException {
        // E:\IdeaWorke-space\bzmall\bzmall-admin\src\main\webapp\img\logo.JPG

        List<PmsBrand> pmsBrands = pmsBrandService.list();

        /**
         * 处理路径
         * E:\IdeaWorke-space\bzmall\bzmall-admin\src\main\webapp\img\logo.JPG
         *
         */

        for (PmsBrand p:pmsBrands){
            p.setLogo("E:\\IdeaWorke-space\\bzmall\\bzmall-admin\\src\\main\\webapp\\img\\"+p.getLogo());
        }

        ExportParams params = new ExportParams("所有品牌数据", "brands");

        /**
         * ExcelExportUtil Excel导出工具类
         * exportExcel 导出方法
         * 参数1 导出参数对象
         * 参数2 普通java队形的类对象 要导入导出的类
         * 参数3 要导出数据集合
         *
         */
        Workbook workbook = ExcelExportUtil.exportExcel(params, PmsBrand.class, pmsBrands);

        workbook.write(new FileOutputStream("E://brands.xlsx"));
    }

    /**
     * 图片导入
     */
    @Test
    public void testInputPhoto() throws Exception {
        FileInputStream inputStream = new FileInputStream("E://brands.xlsx");
        ImportParams importParams = new ImportParams();
        importParams.setHeadRows(1);
        importParams.setTitleRows(1);

        List<PmsBrand>  brands = ExcelImportUtil.importExcel(inputStream, PmsBrand.class, importParams);
        brands.forEach(System.err::println);

        System.out.println("-------------------------------------");
        /*for (PmsBrand p:brands){
            String logo = p.getLogo();
            String s = StrUtil.removePrefix(logo, "E:\\IdeaWorke-space\\bzmall\\bzmall-admin\\src\\main\\webapp\\img\\\\");
            p.setLogo(s);
        }*/
        brands.stream().forEach(PmsBrand ->{
            /**
             * StrUtil.removePrefix(String s1,String s2)
             * 根据s2分割字符串s1
             */
            String removePrefix = StrUtil.removePrefix(PmsBrand.getLogo(), "E:\\IdeaWorke-space\\bzmall\\bzmall-admin\\src\\main\\webapp\\img\\\\");
            PmsBrand.setLogo(removePrefix);
        });
        brands.forEach(System.err::println);

    }

    @Test
    public void test(){

    }
}
