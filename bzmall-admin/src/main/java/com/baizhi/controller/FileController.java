package com.baizhi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/file")
public class FileController {

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


}
