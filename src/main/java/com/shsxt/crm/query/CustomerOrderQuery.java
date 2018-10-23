/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CustomerOrderQuery
 * Author:   Yuan
 * Date:     2018/10/22 15:26
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
 * @create 2018/10/22
 * @since 1.0.0
 */
public class CustomerOrderQuery extends BaseQuery {
    private Integer cusId;

    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }
}
