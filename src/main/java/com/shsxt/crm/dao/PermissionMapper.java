package com.shsxt.crm.dao;

import com.shsxt.crm.base.BaseDao;
import com.shsxt.crm.po.Permission;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionMapper extends BaseDao<Permission> {
    public Integer queryModulesByRoleId(Integer roleId);
    public Integer deletePermissionByRoleId(Integer roleId);

    public Integer queryModuleByAclValue(String aclValue);
    public Integer deleteModuleByAclValue(String aclValue);
}