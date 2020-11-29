package com.baizhi;

import com.aliyun.oss.OSS;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BzMallApplication.class)
public class OSSTest {
    @Autowired
    private OSS ossClient;

    @Test
    public void test2(){
        String bucketName = "bzmall-6";
        String objectName = "brand/aaa.png";
        /**
         * deleteObject(bucketName,objectName);
         * 删除文件，
         * bucketName   文件所在对象存储仓库名
         * objectName   路径及文件名
         */
        // ossClient.deleteObject(bucketName,objectName);

        /**
         * putObject()
         * 上传文件
         */
        ossClient.putObject(bucketName,objectName,new File("C:\\Users\\Administrator\\Pictures\\1_4.jpg"));
    }

    @Test
    public void test3(){
        // 获取源文件名
        String filename = "abc.jpg";
        // 获取文件后缀
        String substring = filename.substring(filename.lastIndexOf("."));

        String bucketName = "bzmall-6";

        // 拼接为新文件名
        String objectName = "brand/";
        StringBuilder sb = new StringBuilder();
        sb.append(objectName);
        sb.append(UUID.randomUUID().toString().substring(0,5));
        sb.append(substring);

        System.err.println(sb.toString());
    }
}
