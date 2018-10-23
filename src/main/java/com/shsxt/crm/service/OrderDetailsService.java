/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: OrderDetailsService
 * Author:   Yuan
 * Date:     2018/10/22 15:21
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.shsxt.crm.service;


import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.dao.OrderDetailsMapper;
import com.shsxt.crm.po.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 *
 *
 * @author Yuan
 * @create 2018/10/22
 * @since 1.0.0
 */
@Service
public class OrderDetailsService extends BaseService<OrderDetails>{

    @Autowired
    private OrderDetailsMapper orderDetailsMapper;
}
