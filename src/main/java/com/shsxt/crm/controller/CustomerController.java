/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CustomerController
 * Author:   Yuan
 * Date:     2018/10/22 10:23
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.shsxt.crm.controller;


import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.po.Customer;
import com.shsxt.crm.query.CusDevPlanQuery;
import com.shsxt.crm.query.CustomerQuery;
import com.shsxt.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 *
 *
 *
 * @author Yuan
 * @create 2018/10/22
 * @since 1.0.0
 */
@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("index")
    public String index(){
        return "customer";
    }

    @RequestMapping("queryCustomersByParams")
    @ResponseBody
    public Map<String, Object> queryCustomersByParams(@RequestParam(defaultValue = "1") Integer page,
                                                        @RequestParam(defaultValue = "10") Integer rows,
                                                        CustomerQuery query){
        query.setPageNum(page);
        query.setPageSize(rows);
        return customerService.queryForPage(query);
    }

    @RequestMapping("saveOrUpdateCustomer")
    @ResponseBody
    public ResultInfo saveOrUpdateCustomer(Customer customer){
         customerService.saveOrUpdateCustomer(customer);
         return success(CrmConstant.OPS_SUCCESS_MSG);
    }

    @RequestMapping("queryDataDicsByDicName")
    @ResponseBody
    public List<Map> queryDataDicsByDicName(String dicName){
        return customerService.queryDataDicsByDicName(dicName);
    }

    @RequestMapping("deleteCustomerBatch")
    @ResponseBody
    public ResultInfo deleteCustomerBatch(Integer[] ids){
        customerService.deleteBatch(ids);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }

    @RequestMapping("addLossCustomers")
    @ResponseBody
    public ResultInfo addLossCustomers(){
        customerService.addLossCustomers();
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }
}
