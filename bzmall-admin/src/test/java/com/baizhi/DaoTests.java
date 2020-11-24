package com.baizhi;

import com.baizhi.dao.BzMenuMapper;
import com.baizhi.entity.BzMenu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BzMallApplication.class)
public class DaoTests extends AppRunTests{
    @Autowired
    private BzMenuMapper bzMenuMapper;

    @Test
    public void test(){
        List<BzMenu> menus = bzMenuMapper.selectMenusbySql();
        menus.forEach(System.err::println);

    }
}
