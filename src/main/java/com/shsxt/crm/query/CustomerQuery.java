/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CustomerQuery
 * Author:   Yuan
 * Date:     2018/10/22 10:13
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.shsxt.crm.query;


import com.shsxt.crm.base.BaseQuery;
import com.shsxt.crm.po.Customer;

/**
 *
 *
 *
 * @author Yuan
 * @create 2018/10/22
 * @since 1.0.0
 */
public class CustomerQuery extends BaseQuery {
    private String khno;

    private String name;

    private String fr;

    public String getKhno() {
        return khno;
    }

    public void setKhno(String khno) {
        this.khno = khno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }
}
