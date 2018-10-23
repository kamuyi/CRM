/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CusDevPlanService
 * Author:   Yuan
 * Date:     2018/10/16 14:53
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.shsxt.crm.service;


import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.dao.CusDevPlanMapper;
import com.shsxt.crm.dao.SaleChanceMapper;
import com.shsxt.crm.po.CusDevPlan;
import com.shsxt.crm.po.SaleChance;
import com.shsxt.crm.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *
 *
 *
 * @author Yuan
 * @create 2018/10/16
 * @since 1.0.0
 */
@Service
public class CusDevPlanService extends BaseService<CusDevPlan> {

    @Autowired
    private CusDevPlanMapper cusDevPlanMapper;

    @Autowired
    private SaleChanceMapper saleChanceMapper;
    /**
     * 保存或者更新计划
     */

    public void saveOrUpdateCusDevPlan(CusDevPlan cusDevPlan,Integer sid){
        /**
         * 1.参数校验
         * 2.补全参数
         * 3.根据id区分是添加还是更新
         * 4.执行操作
         */
        checkCusDevPlanParams(cusDevPlan);
        cusDevPlan.setUpdateDate(new Date());

        Integer id = cusDevPlan.getId();

        //添加操作
        if(id==null){
            cusDevPlan.setCreateDate(new Date());
            //有效
            cusDevPlan.setIsValid(1);
            // 营销机会id
            cusDevPlan.setSaleChanceId(sid);

            /**
             * 判断是否未开发
             */

            SaleChance saleChance = saleChanceMapper.queryById(id);
            if(saleChance.getDevResult()==0){
                saleChance.setDevResult(1);
                AssertUtil.isTrue(cusDevPlanMapper.save(cusDevPlan)<1, CrmConstant.OPS_FAILED_MSG);
            }
        }else{
            //保存操作
            AssertUtil.isTrue(cusDevPlanMapper.update(cusDevPlan)<1, CrmConstant.OPS_FAILED_MSG);
        }
    }

    /**
     * 参数校验
     * @param cusDevPlan
     */
    private void checkCusDevPlanParams(CusDevPlan cusDevPlan) {
        AssertUtil.isTrue(null==cusDevPlan.getPlanDate(),"计划日期为空");
        AssertUtil.isTrue(StringUtils.isBlank(cusDevPlan.getPlanItem()),"计划内容为空");
        AssertUtil.isTrue(StringUtils.isBlank(cusDevPlan.getExeAffect()),"计划结果为空");
    }


}
