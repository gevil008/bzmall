package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.hutool.core.util.StrUtil;
import com.baizhi.entity.PmsBrand;
import com.baizhi.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    private PmsBrandService pmsBrandService;

    @RequestMapping("/upload")
    @ResponseBody
    public Map uploadFile(MultipartFile pic, HttpServletRequest request) throws IOException {

        String realPath = request.getSession().getServletContext().getRealPath("/img/");
        System.err.println(realPath);

        // 获取源文件名
        String filename = pic.getOriginalFilename();
        // 获取源文件后缀
        String prefix = filename.substring(filename.lastIndexOf("."));
        // 拼接为新文件名
        String uuid = UUID.randomUUID().toString();
        StringBuffer sb = new StringBuffer();
        sb.append(uuid);
        sb.append(prefix);

        Map map = new HashMap();
        // 将文件读入到磁盘中
        FileCopyUtils.copy(pic.getInputStream(),new FileOutputStream(realPath+sb.toString()));
        map.put("path",sb.toString());
        map.put("status","success");
        return map;
    }

    @RequestMapping("/remove")
    @ResponseBody
    public Map remove(String file, HttpServletRequest request){
        System.err.println(file);
        String realPath = request.getSession().getServletContext().getRealPath("/img/");
        File file1 = new File(realPath+file);
        // 从磁盘中删除文件
        file1.delete();
        return null;
    }

    @RequestMapping("/batchUpload")
    @ResponseBody
    public Map uploadxlsxFile(MultipartFile xlsxfile) throws Exception {
        InputStream inputStream = xlsxfile.getInputStream();
        ImportParams importParams = new ImportParams();
        importParams.setHeadRows(1);
        importParams.setTitleRows(1);

        List<PmsBrand> brands = ExcelImportUtil.importExcel(inputStream, PmsBrand.class, importParams);
        brands.forEach(System.err::println);

        /*for (PmsBrand p:brands){
            String logo = p.getLogo();
            String s = StrUtil.removePrefix(logo, "E:\\IdeaWorke-space\\bzmall\\bzmall-admin\\src\\main\\webapp\\img\\");
            p.setLogo(s);
        }*/
        brands.stream().forEach(PmsBrand ->{
            /**
             * StrUtil.removePrefix(String s1,String s2)
             * 根据s2分割字符串s1 得到s1剩余的部分
             */
            String removePrefix = StrUtil.removePrefix(PmsBrand.getLogo(), "E:\\IdeaWorke-space\\bzmall\\bzmall-admin\\src\\main\\webapp\\img\\\\");
            PmsBrand.setLogo(removePrefix);
        });
        brands.forEach(System.err::println);
        // pmsBrandService.saveBatch(brands);
        Map map = new HashMap();
        map.put("status","success");
        return map;
    }


}
