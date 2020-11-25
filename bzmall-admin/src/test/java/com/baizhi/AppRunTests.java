package com.baizhi;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @SpringBootTest 为了加载Spring工厂 classes 参数为启动类的类对象
 * @RunWith(SpringJUnit4ClassRunner.class) 加载测试环境
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {BzMallApplication.class})
public class AppRunTests {


}
