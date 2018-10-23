/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: RoleController
 * Author:   Yuan
 * Date:     2018/10/18 11:14
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.shsxt.crm.controller;


import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.dto.UserDto;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.po.Role;
import com.shsxt.crm.query.RoleQuery;
import com.shsxt.crm.query.UserQuery;
import com.shsxt.crm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 *
 *
 *
 * @author Yuan
 * @create 2018/10/18
 * @since 1.0.0
 */
@Controller
@RequestMapping("role")
public class RoleController extends BaseController{

    @Autowired
    private RoleService roleService;

    @RequestMapping("index")
    public String index(){
        return "role";
    }

    @ResponseBody
    @RequestMapping("saveOrUpdateRole")
    public ResultInfo saveOrUpdateRole(Role role){
        roleService.saveOrUpdateRole(role);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }


    /**
     * 查询角色信息
     */
    @RequestMapping("queryRolesByParams")
    @ResponseBody
    public Map<String, Object> queryRolesByParams(@RequestParam(defaultValue = "1") Integer page,
                                                  @RequestParam(defaultValue = "10") Integer rows,
                                                  RoleQuery query){
        query.setPageNum(page);
        query.setPageSize(rows);
        return roleService.queryForPage(query);
    }

    /**
     *
     * @return
     */
    @RequestMapping("queryAllRoles")
    @ResponseBody
    public List<Map> queryAllRoles(){
        return roleService.queryAllRoles();
    }

    /**
     * 权限更新
     */
    @RequestMapping("doGrant")
    @ResponseBody
    public ResultInfo doGrant(Integer roleId,Integer[] moduleIds){
        roleService.doGrant(roleId, moduleIds);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }

    @RequestMapping("deleteRoleBatch")
    @ResponseBody
    public ResultInfo deleteRoleBatch(Integer[] ids){
        roleService.deleteRoleBatch(ids);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }
}
