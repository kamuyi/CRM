/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserInfo
 * Author:   Yuan
 * Date:     2018/10/13 14:58
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.shsxt.crm.model;


/**
 *
 *
 *
 * @author Yuan
 * @create 2018/10/13
 * @since 1.0.0
 */
public class UserInfo {
    /**
     * id 的加密字符串
     */
    private String userIdStr;
    private String userName;
    private String realName;

    public String getUserIdStr() {
        return userIdStr;
    }

    public void setUserIdStr(String userIdStr) {
        this.userIdStr = userIdStr;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
