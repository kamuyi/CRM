/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: RoleService
 * Author:   Yuan
 * Date:     2018/10/18 11:12
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
import com.shsxt.crm.dao.RoleMapper;
import com.shsxt.crm.dao.UserRoleMapper;
import com.shsxt.crm.po.Permission;
import com.shsxt.crm.po.Role;
import com.shsxt.crm.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
@Service
public class RoleService extends BaseService<Role> {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private ModuleMapper moduleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 角色删除
     * 因为删除可能多个，所以传进来数组
     */
    public void deleteRoleBatch(Integer[] roleIds){
        //判断数组是否为空
        /*
            1.删除角色需要把user_role表中的数据删除，即删用户
            2.还需要把perssion表中的数据删除，即删除权限
         */
        if(roleIds!=null && roleIds.length>0 ){
            //因为传进来的是数组所以需要遍历
            for(Integer roleId:roleIds){
                //删角色
                AssertUtil.isTrue(roleMapper.delete(roleId)<1,"删除失败");

                //判断用户是否存在权限，如果没有就不需要删除
                Integer num=permissionMapper.queryModulesByRoleId(roleId);
                if(num>0){
                    AssertUtil.isTrue(permissionMapper.deletePermissionByRoleId(roleId)<num,"权限删除失败");
                }
                //最后删除用户
                //判断角色拥有的用户数量
                Integer num2= userRoleMapper.queryUserRolesByRoleId(roleId);
                if(num2>0){
                    AssertUtil.isTrue(userRoleMapper.deleteUserRolesByRoleId(roleId)<num2,CrmConstant.OPS_FAILED_MSG);
                }
            }
        }
    }

    /**
     * 角色授权
     * @param roleId
     * @param moduleIds
     */
    public void doGrant(Integer roleId,Integer[] moduleIds){
        //检验参数
        AssertUtil.isTrue(null==roleId,"角色ID为空");

        AssertUtil.isTrue(roleMapper.queryById(roleId)==null,"角色不存在");

        //检测传到后台的moduleIdS是否为空
        if(moduleIds!=null && moduleIds.length>0){
            //需要先删除
            //1.判断该用户是否有这些权限，如果有则直接添加，不需要删除
            Integer integer = permissionMapper.queryModulesByRoleId(roleId);

            if(integer>0){
                //删除权限
                AssertUtil.isTrue(permissionMapper.deletePermissionByRoleId(roleId)<integer,CrmConstant.OPS_FAILED_MSG);
            }

            //将moduleIds转换成List
            List<Permission> permissions =new ArrayList<>();

            //遍历moduleIds
            for(Integer moduleId:moduleIds){
                Permission permission =new Permission();
                permission.setRoleId(roleId);
                permission.setUpdateDate(new Date());
                permission.setCreateDate(new Date());
                permission.setModuleId(moduleId);
                // 权限码
                permission.setAclValue(moduleMapper.queryById(moduleId).getOptValue());

                permissions.add(permission);

            }
            AssertUtil.isTrue(permissionMapper.saveBatch(permissions)< permissions.size(),CrmConstant.OPS_FAILED_MSG);
        }
    }

    /**
     * 添加或更新角色
     * @param role
     */
    public void saveOrUpdateRole(Role role){
        //校验角色名
        String roleName=role.getRoleName();
        AssertUtil.isTrue(StringUtils.isBlank(roleName),"角色名称不能为空");
        role.setUpdateDate(new Date());

        //通过ID判断是添加还是更新
        Integer id=role.getId();
        if(id==null){
            //添加操作
            //1.判断添加的角色名称是否存在
            AssertUtil.isTrue(roleMapper.queryRoleByName(roleName) != null,"用户已存在");
            //补全参数
            role.setCreateDate(new Date());
            role.setIsValid(1);
            AssertUtil.isTrue(roleMapper.save(role)<1, CrmConstant.OPS_FAILED_MSG);
        }else{
            //更新操作
            //1.判断是否是原来的角色名称
            String roleName1=roleMapper.queryById(id).getRoleName();
            if(!roleName.equals(roleName1)){
                //如果两个角色不相等，判断新的是否存在
                AssertUtil.isTrue(roleMapper.queryRoleByName(roleName)!=null,"用户已存在");

            }

            AssertUtil.isTrue(roleMapper.update(role)<1, CrmConstant.OPS_FAILED_MSG);
        }
    }

    /**
     * 查询所有角色
     * @return
     */
    public List<Map> queryAllRoles(){
        return roleMapper.queryAllRoles();
    }
}
