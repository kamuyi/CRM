/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CustomerService
 * Author:   Yuan
 * Date:     2018/10/22 10:21
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.shsxt.crm.service;


import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.dao.CustomerLossMapper;
import com.shsxt.crm.dao.CustomerMapper;
import com.shsxt.crm.po.Customer;
import com.shsxt.crm.po.CustomerLoss;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.utils.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
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
@Service
public class CustomerService extends BaseService<Customer> {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerLossMapper customerLossMapper;

    /**
     * 添加或者更新操作
     *
     */
    public void saveOrUpdateCustomer(Customer customer){
        //校验参数.省略
        Integer id=customer.getId();
        customer.setUpdateDate(new Date());

        //判断是添加还是更新
        if(id==null){
            //添加
            customer.setCreateDate(new Date());
            customer.setIsValid(1);
            customer.setState(0);
            customer.setKhno(MathUtil.genereateKhCode());
            AssertUtil.isTrue(customerMapper.save(customer)<1, CrmConstant.OPS_FAILED_MSG);
        }else {
            AssertUtil.isTrue(customerMapper.update(customer)<1, CrmConstant.OPS_FAILED_MSG);
        }
    }


    public List<Map> queryDataDicsByDicName(String dicName){
        return customerMapper.queryDataDicsByDicName(dicName);
    }


    public void addLossCustomers(){

        List<Customer> customerList=customerMapper.queryLossCustomers();
        if(!CollectionUtils.isEmpty(customerList)){
            // 存流失客户列表
            List<CustomerLoss> customerLossList = new ArrayList<>();

            for(Customer customer : customerList){
                CustomerLoss customerLoss = new CustomerLoss();
                customerLoss.setCusNo(customer.getKhno());
                customerLoss.setCusName(customer.getName());
                customerLoss.setCusManager(customer.getCusManager());
                customerLoss.setState(0);
                customerLoss.setIsValid(1);
                customerLoss.setCreateDate(new Date());
                customerLoss.setUpdateDate(new Date());
                customerLossList.add(customerLoss);
            }
            AssertUtil.isTrue(customerLossMapper.saveBatch(customerLossList)<customerLossList.size(), CrmConstant.OPS_FAILED_MSG);
            AssertUtil.isTrue(customerMapper.updateCustomerState(customerList)<customerList.size(), CrmConstant.OPS_FAILED_MSG);
        }

    }
}
