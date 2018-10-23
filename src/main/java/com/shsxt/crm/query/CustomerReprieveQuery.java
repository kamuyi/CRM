/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CustomerReprieveQuery
 * Author:   Yuan
 * Date:     2018/10/23 11:20
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
public class CustomerReprieveQuery extends BaseQuery {
    private Integer lossId;

    public Integer getLossId() {
        return lossId;
    }

    public void setLossId(Integer lossId) {
        this.lossId = lossId;
    }
}
