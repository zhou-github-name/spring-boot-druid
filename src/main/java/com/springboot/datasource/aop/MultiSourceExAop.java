package com.springboot.datasource.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.springboot.datasource.annotion.DataSourceMore;
import com.springboot.datasource.mutidatesource.DSEnum;
import com.springboot.datasource.mutidatesource.DataSourceContextHolder;

import lombok.extern.slf4j.Slf4j;

/**
 * 多数据源切换的aop
 *
 * @author zy
 */
@Aspect
@Component
@ConditionalOnProperty(prefix = "xncoding", name = "muti-datasource-open", havingValue = "true")
@Slf4j
public class MultiSourceExAop implements Ordered {

    @Pointcut(value = "@annotation(com.springboot.datasource.annotion.DataSourceMore)")
    private void cut() {

    }

    @Around("cut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        Signature signature = point.getSignature();
        MethodSignature methodSignature = null;
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        methodSignature = (MethodSignature) signature;

        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());

        DataSourceMore datasource = currentMethod.getAnnotation(DataSourceMore.class);
        if (datasource != null) {
            DataSourceContextHolder.setDataSourceType(datasource.name());
            log.info("设置数据源为：" + datasource.name());
        } else {
            DataSourceContextHolder.setDataSourceType(DSEnum.DATA_SOURCE_CORE);
            log.info("设置数据源为：dataSourceCore");
        }
        try {
            return point.proceed();
        }finally {
            log.info("清空数据源信息！");
            DataSourceContextHolder.clearDataSourceType();
        }
    }


    /**
     * aop的顺序要早于spring的事务
     */
    
    public int getOrder() {
        return 1;
    }

}
