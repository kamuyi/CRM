/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: PermissionAdaptor
 * Author:   Yuan
 * Date:     2018/10/21 9:18
 * Description: 权限AOP
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.shsxt.crm.aop;


import com.shsxt.crm.annotations.RequestPermission;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.utils.AssertUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.List;

/**
 *
 * 权限AOP
 *
 * @author Yuan
 * @create 2018/10/21
 * @since 1.0.0
 */
@Component
@Aspect
public class PermissionAdaptor {

    @Autowired
    private HttpSession session;

    @Pointcut("@annotation(com.shsxt.crm.annotations.RequestPermission)")
    public void cut(){}


    @Around("cut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {

        Object result=null;

        MethodSignature methodSignature= (MethodSignature) pjp.getSignature();
        Method method=methodSignature.getMethod();
        RequestPermission requestPermission=method.getAnnotation(RequestPermission.class);

        //获取注解中的aclvalue的值
        String aclValue=requestPermission.aclValue();

        //判断目标权限码是否包含在当前用户权限列表之中
        //获取session 中的列表
        List<String> permissions= (List<String>) session.getAttribute(CrmConstant.USER_PERMISSIONS);

        AssertUtil.isTrue(CollectionUtils.isEmpty(permissions) || !permissions.contains(aclValue),"没有权限");

        result = pjp.proceed();

        return result;

    }
}
