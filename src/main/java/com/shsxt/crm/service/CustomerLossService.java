/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CustomerLossService
 * Author:   Yuan
 * Date:     2018/10/23 10:24
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.shsxt.crm.service;


import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.dao.CustomerLossMapper;
import com.shsxt.crm.po.CustomerLoss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 *
 *
 * @author Yuan
 * @create 2018/10/23
 * @since 1.0.0
 */
@Service
public class CustomerLossService extends BaseService<CustomerLoss> {

    @Autowired
    private CustomerLossMapper customerLossMapper;
}
