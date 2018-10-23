/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: SaleChanceService
 * Author:   Yuan
 * Date:     2018/10/15 13:35
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.shsxt.crm.service;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.dao.SaleChanceMapper;
import com.shsxt.crm.dao.UserMapper;
import com.shsxt.crm.po.SaleChance;
import com.shsxt.crm.po.User;
import com.shsxt.crm.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 *营销机会
 *
 * @author Yuan
 * @create 2018/10/15
 * @since 1.0.0
 */
@Service
public class SaleChanceService extends BaseService<SaleChance>{

    @Autowired
    private SaleChanceMapper saleChanceMapper;

    @Autowired
    private UserMapper userMapper;


    public List<Map> queryAllCustomerManager(){
        return saleChanceMapper.queryAllCustomerManager();
    }


    /**
     * 添加或者更新操作
     * @param saleChance
     */

    public void saveOrUpdateSaleChance(SaleChance saleChance,Integer userId){
        /**
         * 1.检验参数
         * 2.补全参数
         * 3. 通过id区分是添加或者更新
         * 4. 执行最终操作
         */
        checkSaleChanceParams(saleChance.getCustomerName(), saleChance.getLinkMan(),
                saleChance.getLinkPhone());

        //设置更新事件
        saleChance.setUpdateDate(new Date());

        Integer id=saleChance.getId();

        //判断id是否为空
        if(id==null){
            //添加操作

            /***
             * 如果选择分配人, state 为 1 已分配, 设置分配时间
             * 如果未选择分配人, state 为 0 未分配
             * */
            if(StringUtils.isBlank(saleChance.getAssignMan())){
                saleChance.setState(0);
            }else{
                saleChance.setState(1);
                saleChance.setAssignTime(new Date());
            }


            // 未开发
            saleChance.setDevResult(0);
            // 有效数据
            saleChance.setIsValid(1);
            // 创建事件
            saleChance.setCreateDate(new Date());
            User user = userMapper.queryById(userId);
            // 创建人
            saleChance.setCreateMan(user.getUserName());

            AssertUtil.isTrue(saleChanceMapper.save(saleChance)<1, CrmConstant.OPS_FAILED_MSG);
        }else{

            //更新
            AssertUtil.isTrue(saleChanceMapper.update(saleChance)<1, CrmConstant.OPS_FAILED_MSG);
        }


    }

    private void checkSaleChanceParams(String customerName, String linkMan, String linkPhone) {
        AssertUtil.isTrue(StringUtils.isBlank(customerName),"客户名称不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(linkMan),"联系人不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(linkPhone),"联系电话不能为空");
    }

    public Integer updateSaleChanceDevResult(SaleChance saleChance){
        return saleChanceMapper.updateSaleChanceDevResult(saleChance);
    }
}
