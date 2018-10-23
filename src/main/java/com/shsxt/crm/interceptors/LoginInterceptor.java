/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: LoginInterceptor
 * Author:   Yuan
 * Date:     2018/10/15 10:58
 * Description: 登录拦截
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.shsxt.crm.interceptors;


import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 登录拦截
 *
 * @author Yuan
 * @create 2018/10/15
 * @since 1.0.0
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /**
         * 判断用户是否登录
         *
         */
        //从cookie获取用户ID
        Integer userId= LoginUserUtil.releaseUserIdFromCookie(request);
        //断言
        AssertUtil.isNotLogin(null==userId || null==userService.queryById(userId), CrmConstant.USER_NOT_LOGIN_MSG);
        //继续执行
        return  true;
    }
}
