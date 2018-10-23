/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: RoleQuery
 * Author:   Yuan
 * Date:     2018/10/19 9:23
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.shsxt.crm.query;


import com.shsxt.crm.base.BaseQuery;

import java.util.Date;

/**
 *
 *
 *
 * @author Yuan
 * @create 2018/10/19
 * @since 1.0.0
 */
public class RoleQuery extends BaseQuery {
    private String roleName;
    private String createDate;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
