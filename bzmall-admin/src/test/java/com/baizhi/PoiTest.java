package com.baizhi;

import com.baizhi.entity.PmsBrand;
import com.baizhi.service.PmsBrandService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BzMallApplication.class)
public class PoiTest {

    @Autowired
    private PmsBrandService pmsBrandService;

    @Test
    public void test() throws IOException {
        /**
         * 准备数据
         */
        String[] titles = {"品牌id","品牌名字","首字母","排序","显示状态","logo","描述"};
        List<PmsBrand> pmsBrands = pmsBrandService.list();

//        创建文件对象
        HSSFWorkbook workbook = new HSSFWorkbook();

//        创建表
        HSSFSheet sheet = workbook.createSheet("brands");

        /**
         * 写入表头
         */
        HSSFRow titleRow = sheet.createRow(0);

        for (int i = 0; i < titles.length; i++) {
//            创建单元格
            HSSFCell cell = titleRow.createCell(i);

            cell.setCellValue(titles[i]);
        }

        /**
         * 写入品牌表的数据
         */
        for (int i = 0; i < pmsBrands.size(); i++) {
//            创建行
            HSSFRow row = sheet.createRow(i + 1);

//            获得品牌对象
            PmsBrand brand = pmsBrands.get(i);

//            将数据写入到行中
            row.createCell(0).setCellValue(brand.getBrandId());
            row.createCell(1).setCellValue(brand.getName());
            row.createCell(2).setCellValue(brand.getFirstLetter());
            row.createCell(3).setCellValue(brand.getSort());
            row.createCell(4).setCellValue(brand.getShowStatus());
            row.createCell(5).setCellValue(brand.getLogo());
            row.createCell(6).setCellValue(brand.getBrandStory());
        }

        workbook.write(new FileOutputStream("E://brands.xls"));

    }

    @Test
    public void test1(){
        List<PmsBrand> list = pmsBrandService.list();
        list.forEach(System.err::println);
    }

    @Test
    public void test3() throws IOException {
       // 通过流读取数据
        FileInputStream inputStream = new FileInputStream("E://brands.xls");

       // 通过Poi解析流
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

       // 通过文件对象获取表对象  getSheetAt 根据下标获取表
        HSSFSheet sheet = workbook.getSheetAt(0);

       // 根据表获取行
       // 获取最后一行的下标  1--->最后一行的下标
        int lastRowNum = sheet.getLastRowNum();

        for (int i = 1; i <= lastRowNum; i++) {
           // 获取行
            HSSFRow row = sheet.getRow(i);
           // 获取行中的数据 封装到对象中
            PmsBrand pmsBrand = new PmsBrand();
            /**
             * getNumericCellValue 获取数字类型
             * getStringCellValue 获取String类型
             */
            double id = row.getCell(0).getNumericCellValue();
            pmsBrand.setBrandId((long) id);
            String name = row.getCell(1).getStringCellValue();
            pmsBrand.setName(name);
            pmsBrand.setFirstLetter(row.getCell(2).getStringCellValue());
            pmsBrand.setSort((int) row.getCell(3).getNumericCellValue());
            pmsBrand.setShowStatus((int) row.getCell(4).getNumericCellValue());
            pmsBrand.setLogo(row.getCell(5).getStringCellValue());
            pmsBrand.setBrandStory(row.getCell(6).getStringCellValue());

            System.err.println(pmsBrand);
        }
    }

    @Test
    public void test4(){
        PmsBrand pmsBrand = new PmsBrand();
        Class aClass = pmsBrand.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field f:declaredFields){
            System.err.println(f.getName());
        }

    }
}
