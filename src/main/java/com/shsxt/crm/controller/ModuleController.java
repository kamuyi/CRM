/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ModuleController
 * Author:   Yuan
 * Date:     2018/10/19 13:49
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.shsxt.crm.controller;


import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.dto.ModuleDto;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.po.Module;
import com.shsxt.crm.query.ModuleQuery;
import com.shsxt.crm.query.SaleChanceQuery;
import com.shsxt.crm.service.ModuleService;
import com.shsxt.crm.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.management.Query;
import java.util.List;
import java.util.Map;

/**
 *
 *
 *
 * @author Yuan
 * @create 2018/10/19
 * @since 1.0.0
 */
@Controller
@RequestMapping("module")
public class ModuleController extends BaseController{

    @Autowired
    private ModuleService moduleService;

    @RequestMapping("index")
    public String index(){
        return "module";
    }

    @RequestMapping("deleteModule")
    @ResponseBody
    public ResultInfo deleteModule(Integer[] ids){
        moduleService.deleteModule(ids);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }


    /**
     * 角色权限数据回显
     * @param roleId
     * @return
     */
    @RequestMapping("queryAllModuleByRoleId")
    @ResponseBody
    public List<ModuleDto> queryAllModuleByRoleId(Integer roleId){
        return moduleService.queryAllModuleByRoleId(roleId);
    }

    @RequestMapping("queryModulesByParams")
    @ResponseBody
    public Map<String, Object> queryModulesByParams(@RequestParam(defaultValue = "1") Integer page,
                                                        @RequestParam(defaultValue = "10") Integer rows,
                                                        ModuleQuery query){
        query.setPageNum(page);
        query.setPageSize(rows);
        return moduleService.queryForPage(query);
    }

    @RequestMapping("queryModulesByGrade")
    @ResponseBody
    public List<Map> queryModuleByGrade(Integer grade){
        return moduleService.queryModuleByGrade(grade);
    }

    @RequestMapping("saveOrUpdateModule")
    @ResponseBody
    public ResultInfo saveOrUpdateModule(Module module){
        moduleService.saveOrUpdateModule(module);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }
}
