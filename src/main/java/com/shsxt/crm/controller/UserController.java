/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserController
 * Author:   Yuan
 * Date:     2018/10/13 11:27
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.shsxt.crm.controller;


import com.shsxt.crm.annotations.RequestPermission;
import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.dto.UserDto;
import com.shsxt.crm.exceptions.ParamsException;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.model.UserInfo;
import com.shsxt.crm.po.User;
import com.shsxt.crm.query.SaleChanceQuery;
import com.shsxt.crm.query.UserQuery;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *
 *
 *
 * @author Yuan
 * @create 2018/10/13
 * @since 1.0.0
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("saveOrUpdateUser")
    public ResultInfo saveOrUpdateUser(UserDto user, Integer[] roleIds){
        userService.saveOrUpdateUser(user,roleIds);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }

    @RequestMapping("index")
    public String index(){
        return "user";
    }

    /**
     * 修改密码
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     * @param request
     * @return
     */
    @RequestPermission(aclValue = "6040")
    @ResponseBody
    @RequestMapping("updateUserPwd")
    public ResultInfo updateUserPwd(String oldPassword,
                                    String newPassword,
                                    String confirmPassword,
                                    HttpServletRequest request) {


        //获取用户ID
       Integer userId= LoginUserUtil.releaseUserIdFromCookie(request);
        userService.updateUserPwd(oldPassword,newPassword,confirmPassword,userId);
        return success("修改成功");

    }
    /**
     * 用户登录
     * @param userName
     * @param userPwd
     * @return
     */
    @ResponseBody
    @RequestMapping("login")
    public ResultInfo login(String userName,String userPwd){

        UserInfo userInfo = userService.login(userName, userPwd);
        return success("登陆成功", userInfo);

    }

    /**
     * 查询用户信息
     */
    @RequestMapping("queryUsersByParams")
    @ResponseBody
    public Map<String, Object> queryUsersByParams(@RequestParam(defaultValue = "1") Integer page,
                                                        @RequestParam(defaultValue = "10") Integer rows,
                                                        UserQuery query){
        query.setPageNum(page);
        query.setPageSize(rows);
        return userService.queryForPage(query);
    }

    /**
     * 用户删除
     * @param ids
     * @return
     */
    @RequestMapping("deleteUsers")
    @ResponseBody
    public ResultInfo deleteUsers(Integer[] ids){
        userService.deleteUsers(ids);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }
}
