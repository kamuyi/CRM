/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CustomerLossController
 * Author:   Yuan
 * Date:     2018/10/23 10:23
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.shsxt.crm.controller;


import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.po.CustomerLoss;
import com.shsxt.crm.query.CustomerLossQuery;
import com.shsxt.crm.service.CustomerLossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 *
 *
 *
 * @author Yuan
 * @create 2018/10/23
 * @since 1.0.0
 */
@Controller
@RequestMapping("customerLoss")
public class CustomerLossController extends BaseController {

    @Autowired
    private CustomerLossService customerLossService;

    @RequestMapping("index")
    public String index(){
        return "customer_loss";
    }


    @RequestMapping("queryCustomerLossByParams")
    @ResponseBody
    public Map<String, Object> queryCustomerLossByParams(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer rows,
            CustomerLossQuery query) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return customerLossService.queryForPage(query);
    }

    @RequestMapping("updateCustomerLoss")
    @ResponseBody
    public ResultInfo updateCustomerLoss(CustomerLoss customerLoss) {
        customerLossService.update(customerLoss);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }

}


