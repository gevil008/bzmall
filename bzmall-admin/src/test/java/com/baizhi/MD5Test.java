package com.baizhi;

import cn.hutool.crypto.digest.DigestUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

public class MD5Test extends AppRunTests{

    @Test
    public void test1(){
        String s = DigestUtils.md5Hex("123456");
        System.err.println(s);
        String ss = DigestUtil.md5Hex("123456");
        System.err.println(ss);

        /**
         * 给用户生成随机的字符串
         *
         * 名文+随机字符串 ==》 密文
         *
         * 加盐：给明文添加随机字符串 再加密
         *
         * 随机字符串叫做盐值
         *
         * 散列：对同一个密码多次加密
         */

        String salt = UUID.randomUUID().toString().substring(0,4);
        String s1 = DigestUtils.md5Hex("123456"+salt);
        System.err.println(s1);

        for (int i=0;i<1024;i++){
            s1 = DigestUtils.md5Hex(s1);
        }
        System.err.println(s1);
    }

    @Test
    public void test2(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String s1 = "123456";

        /**
         * 123456
         *
         * $2a$10$vmo2QZbFzKVsG18qvZqTdeS02IruzLmTMPo5gcXwJPaGeGiWI/6qy
         */

        String encode = encoder.encode(s1);
        System.err.println(encode);

        boolean matches = encoder.matches("123456", "$2a$10$vmo2QZbFzKVsG18qvZqTdeS02IruzLmTMPo5gcXwJPaGeGiWI/6qy");
        System.err.println(matches);
    }

}
