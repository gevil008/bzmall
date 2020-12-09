package com.baizhi;

import com.baizhi.entity.BzAdmin;
import com.baizhi.entity.BzAdminRole;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class RedisTests extends AppRunTests{

    /**
     *  RedisTemplate
     *  StringRedisTemplate
     *
     *  RedisTemplate 默认的序列化方式是JDK序列化 可以修改为json序列化方式
     *
     *  StringRedisTemplate 默认序列化是String类型
     */
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test1(){
        /**
         * 操作String类型的对象
         */
        /*ValueOperations operations = redisTemplate.opsForValue();
        operations.set("name","lisi");

        Object name = operations.get("name");
        System.err.println(name);*/

        /*ValueOperations<String, String> value = stringRedisTemplate.opsForValue();
        value.set("name","lisi");
        String name1 = value.get("name");
        System.err.println(name1);*/

        BzAdmin bzAdmin = new BzAdmin(null, "zhagnsan", "lisi");
        BzAdminRole bzAdminRole = new BzAdminRole();
        bzAdminRole.setId(101);
        bzAdminRole.setRoleId(101);
        bzAdminRole.setAdminId(101);

        ValueOperations operations = redisTemplate.opsForValue();
        // operations.set("lisi",bzAdminRole);
        // operations.set("zhangsan",bzAdmin);

        // Object lisi = operations.get("lisi");

        BzAdmin zhagnsan2 = (BzAdmin) operations.get("zhangsan");
        System.err.println(zhagnsan2);
    }

}
