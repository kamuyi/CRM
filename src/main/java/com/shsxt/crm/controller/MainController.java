/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: MainController
 * Author:   Yuan
 * Date:     2018/10/13 10:28
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.shsxt.crm.controller;


import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.po.User;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 *
 *
 * @author Yuan
 * @create 2018/10/13
 * @since 1.0.0
 */
@Controller
public class MainController  extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("main")
    public String index(HttpServletRequest request){
        /**
         * 查询用户，存入requset
         */


        Integer userId= LoginUserUtil.releaseUserIdFromCookie(request);
        User user = userService.queryById(userId);
        request.setAttribute("user",user);
        /**
         * 查询权限列表放入session中
         */
        List<String> permissions=userService.queryAllAclValueByUserId(userId);
        request.getSession().setAttribute(CrmConstant.USER_PERMISSIONS,permissions);

        return "main";
    }
}

