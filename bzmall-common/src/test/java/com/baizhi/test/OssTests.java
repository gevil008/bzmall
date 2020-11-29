package com.baizhi.test;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.junit.Test;

import java.io.File;

public class OssTests {


    @Test
    public void test(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4G11ZfbiYpTMQjEppwrb";
        String accessKeySecret = "FmjIJFmaazOnpZySXjTKsypMXIklBI";

        String bucketName = "bzmall-6";

        // <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        String objectName = "brand/hhhh.png";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件到指定的存储空间（bucketName）并将其保存为指定的文件名称（objectName）。
        ossClient.putObject(bucketName, objectName, new File("C:\\Users\\Administrator\\Pictures\\1_1.jpg"));

        // 关闭OSSClient。
        ossClient.shutdown();
    }

}
