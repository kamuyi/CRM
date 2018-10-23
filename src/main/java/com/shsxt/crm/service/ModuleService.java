/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ModuleService
 * Author:   Yuan
 * Date:     2018/10/19 13:46
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.shsxt.crm.service;


import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.dao.ModuleMapper;
import com.shsxt.crm.dao.PermissionMapper;
import com.shsxt.crm.dto.ModuleDto;
import com.shsxt.crm.po.Module;
import com.shsxt.crm.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
@Service
public class ModuleService extends BaseService<Module> {

    @Autowired
    private ModuleMapper moduleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    public void deleteModule(Integer[] ids){
        AssertUtil.isTrue(null==ids || ids.length <1,"请选择删除的模块");
        for(Integer moduleId:ids){
            /**
             * 1.根据权限码删除当前表
             */
            Module module = moduleMapper.queryById(moduleId);
            String optValue = module.getOptValue();
            //先根据权限码查询是否存在下级
            Integer num=moduleMapper.queryNumByOptValue(optValue);

            if(num>0){
                //存在下级需要删除
                AssertUtil.isTrue(moduleMapper.deleteBatchByOptValue(optValue)<num, CrmConstant.OPS_FAILED_MSG);
            }
            //删除与他关联的中间表
            //1.查询
            Integer integer = permissionMapper.queryModuleByAclValue(optValue);

            if(integer>0){
                AssertUtil.isTrue(permissionMapper.deleteModuleByAclValue(optValue)<integer,CrmConstant.OPS_FAILED_MSG);
            }
        }
    }

    /**
     * 添加或更新操作
     * @param module
     */
    public void saveOrUpdateModule(Module module){
        /**
         * 1. 校验参数
         * 2. 补全参数
         * 3. 判断添加或者更新
         * 4. 执行操作
         * */
        checkModuleParams(module);
        module.setUpdateDate(new Date());
        if(module.getId()==null){
            //添加操作
            module.setCreateDate(new Date());
            module.setIsValid((byte) 1);
            AssertUtil.isTrue(moduleMapper.save(module)<1, CrmConstant.OPS_FAILED_MSG);
        }
    }

    private void checkModuleParams(Module module) {
        /**
         * 非空校验
         */
        //模块名称
        String moduleName=module.getModuleName();
        AssertUtil.isTrue(StringUtils.isBlank(moduleName),"模块名称为空");
        //权限码
        String optValue=module.getOptValue();
        AssertUtil.isTrue(StringUtils.isBlank(optValue),"权限码为空");
        //菜单层级
        Integer grade=module.getGrade();
        AssertUtil.isTrue(null==grade,"菜单层级为空");

        /**
         * 唯一校验
         *
         */
        AssertUtil.isTrue(null!=moduleMapper.queryModuleByName(moduleName),"模块名称重复");
        AssertUtil.isTrue(null!=moduleMapper.queryModuleByOptValue(optValue),"权限码重复");

        /**
         * 权限码格式校验
         * grade:optValue
         *   0  ：10
         *   1  ：1010
         *   2  ：101011
         */
        //1.权限码位数校验
        //获取正常位数
        Integer len=(grade+1)*2;
        AssertUtil.isTrue(len!=optValue.length(),"权限码位数应为"+len+"位");

        //权限码格式
        //只有菜单层级>0才需要判断上级菜单
        if(grade>0){
            Module parentModule=moduleMapper.queryById(module.getParentId());
            Integer parentGrade=parentModule.getGrade();
            //判断菜单层级是否正确
            AssertUtil.isTrue(grade-parentGrade!=1,"菜单层级不正确");

            /**
             * 判断权限码格式是否是父权限码开头
             */
            String parentOptValue=parentModule.getOptValue();
            AssertUtil.isTrue(optValue.indexOf(parentOptValue)!=0,"权限格式不正确,格式应为:"+parentOptValue+"xx");
        }else{
            module.setParentId(null);
        }
    }


    public List<ModuleDto> queryAllModuleByRoleId(Integer roleId){
        return moduleMapper.queryAllModuleByRoleId(roleId);
    }

    public List<Map> queryModuleByGrade(Integer grade){
        return moduleMapper.queryModuleByGrade(grade);
    }
}
