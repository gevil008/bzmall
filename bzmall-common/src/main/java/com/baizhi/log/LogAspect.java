package com.baizhi.log;

import com.baizhi.dao.BzLogMapper;
import com.baizhi.entity.BzAdmin;
import com.baizhi.entity.BzLog;
import com.baizhi.enums.LogTypeEnum;
import com.baizhi.util.IPKit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Aspect 声明当前类为一个切面类
 */
@Aspect
@Configuration
public class LogAspect {

    @Autowired
    private BzLogMapper logMapper;

    @Autowired
    private HttpServletRequest request;

    /**
     * 日志增强方法
     */
    @Around("@annotation(com.baizhi.log.LogAnnotation)")
    public Object logAfter(ProceedingJoinPoint joinPoint){
        /**
         * 1 采集日志信息
         */
        BzLog bzLog = new BzLog();

        // 时间
        bzLog.setLogDate(new Date());

        // 管理员名字
        // RequestContext Request 上下文  --》容器 --》工厂 ApplicationContext
        // ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        BzAdmin admin = (BzAdmin) request.getSession().getAttribute("admin");
        if (admin == null){
            bzLog.setUsername("root");
        } else {
            bzLog.setUsername(admin.getUsername());
        }

        // ip  长使用工具类经进行获取
        // String remoteAddr = request.getRemoteAddr();
        String ipAddrByRequest = IPKit.getIpAddrByRequest(request);
        bzLog.setLogIp(ipAddrByRequest);

        /**
         * 类型和内容
         * 用户调用的方法不同，内容和类型不同 通过自定义注解
         * 获取自定义注解的值 通过与i个对象JoinPoint 连接点对象
         * 可以获取目标方法所有的信息
         */
        // 获取方法签名对象
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取目标方法对象
        Method method = signature.getMethod();
        // 获取方法上的注解
        LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);

        LogTypeEnum type = annotation.type();
        // 获取枚举的变量名
        bzLog.setLogType(type.name());
        bzLog.setLogContent(annotation.content());

        // 获取方法
        bzLog.setLogMethod(method.toString());

        // 执行时间
        long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        bzLog.setLogTime((int) (endTime - startTime));
        /**
         * 2 日志信息添加数据库
         */
        System.err.println(bzLog);
        logMapper.insert(bzLog);

        return result;
    }
}
