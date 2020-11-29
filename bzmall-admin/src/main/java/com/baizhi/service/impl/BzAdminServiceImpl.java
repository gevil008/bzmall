package com.baizhi.service.impl;

import com.baizhi.dao.BzAdminMapper;
import com.baizhi.entity.BzAdmin;
import com.baizhi.log.LogAnnotation;
import com.baizhi.service.BzAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// @Slf4j  // 注解方式
public class BzAdminServiceImpl extends ServiceImpl<BzAdminMapper, BzAdmin> implements BzAdminService {
    @Autowired
    private BzAdminMapper adminDao;
    /**
     * 创建日志打印对象
     *
     * Logger LoggerFactory 都是 org.slf4j包
     * 参数 ：当前的类的类对象
     */
    // private Logger logger = LoggerFactory.getLogger(BzAdminServiceImpl.class);
    @Override
    @LogAnnotation(content = "查询管理员列表信息")
    public List<BzAdmin> selectAllAdmin() {
        /**
         * 使用日志对象打印日志信息 取代打印语句
         *
         * 一般代码的调试 取代打印语句 使用debug 或者 info
         * 警告信息warn 例如：权限操作
         * 异常处理中使用 error
         */
        /*logger.debug("这是debug等级菜单日志");
        logger.info("这是info等级菜单日志");
        logger.warn("这是warn等级菜单日志");
        logger.error("这是error等级菜单日志");*/

        /**
         * 注解方式
         */
        // log.debug("这是使用注解debug等级菜单日志");
        // log.info("这是使用注解info等级菜单日志");

        return adminDao.selectList(null);
    }
}
