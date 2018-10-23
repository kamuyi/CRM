/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserDto
 * Author:   Yuan
 * Date:     2018/10/18 9:59
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.shsxt.crm.dto;


import com.shsxt.crm.po.User;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 *
 * @author Yuan
 * @create 2018/10/18
 * @since 1.0.0
 */
public class UserDto extends User {
    private String roleName;

    /**
     *
        接收字符串 1,2,3
     */
    private String roleIdsStr;
    // 存id [1,2,3]
    private List<Integer> roleIds = new ArrayList<>();

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleIdsStr() {
        return roleIdsStr;
    }

    public void setRoleIdsStr(String roleIdsStr) {
        this.roleIdsStr = roleIdsStr;
    }

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }
}
