/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: SaleChanceQuery
 * Author:   Yuan
 * Date:     2018/10/15 13:25
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.shsxt.crm.query;


import com.shsxt.crm.base.BaseQuery;

/**
 *
 *mapper层SaleChanceMapper中的查询参数的参数类型
 *
 * @author Yuan
 * @create 2018/10/15
 * @since 1.0.0
 */
public class SaleChanceQuery extends BaseQuery{

    private String customerName;
    private Integer state;
    private Integer devResult;
    private String createDate;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getDevResult() {
        return devResult;
    }

    public void setDevResult(Integer devResult) {
        this.devResult = devResult;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
