package com.baizhi.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.baizhi.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/oss")
public class OSSController {
    @Autowired
    private OSS ossClient;

    @RequestMapping("/uploadImg")
    public R uploadImg(MultipartFile file) throws IOException {
        // 获取源文件名
        String filename = file.getOriginalFilename();
        // 获取文件后缀
        String substring = filename.substring(filename.lastIndexOf("."));

        String bucketName = "bzmall-6";

        // 拼接为新文件名
        String objectName = "brand/";
        StringBuilder sb = new StringBuilder();
        sb.append(UUID.randomUUID().toString().substring(0,5));
        sb.append(substring);

        System.err.println(sb.toString());

        try {
            ossClient.putObject(bucketName, objectName+sb.toString(), file.getInputStream());
        } catch (OSSException e) {
            e.printStackTrace();
            return R.error("图片上传失败，请稍后重试");
        }
        return R.ok().put("path",sb.toString());
    }

    @RequestMapping("/removeImg")
    public R removeImg(String file) throws IOException {
        String bucketName = "bzmall-6";

        // 拼接为新文件名
        String objectName = "brand/";

        ossClient.deleteObject(bucketName,objectName+file);

        return R.ok();
    }

}
