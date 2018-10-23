/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CustomerLossQuery
 * Author:   Yuan
 * Date:     2018/10/23 10:27
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
 * @create 2018/10/23
 * @since 1.0.0
 */
public class CustomerLossQuery extends BaseQuery {
    private String cusNo;

    private String cusName;

    private String createDate;

    public String getCusNo() {
        return cusNo;
    }

    public void setCusNo(String cusNo) {
        this.cusNo = cusNo;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
