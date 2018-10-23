/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserQuery
 * Author:   Yuan
 * Date:     2018/10/18 9:58
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.shsxt.crm.query;


import com.shsxt.crm.base.BaseQuery;

/**
 *
 *
 *
 * @author Yuan
 * @create 2018/10/18
 * @since 1.0.0
 */
public class UserQuery extends BaseQuery {
    private String userName;

    private String email;

    private String phone;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
