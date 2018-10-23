/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: SaleChanceController
 * Author:   Yuan
 * Date:     2018/10/15 13:21
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.shsxt.crm.controller;


import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.po.SaleChance;
import com.shsxt.crm.query.SaleChanceQuery;
import com.shsxt.crm.service.SaleChanceService;
import com.shsxt.crm.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 *
 *
 *
 * @author Yuan
 * @create 2018/10/15
 * @since 1.0.0
 */
@Controller
@RequestMapping("saleChance")
public class SaleChanceController extends BaseController {

    @Autowired
    public SaleChanceService saleChanceService;

    @RequestMapping("index/{state}")
    public String index(@PathVariable Integer state){
        if(state==1){
            return "sale_chance";
        }else if(state==2){
            return "cus_dev_plan";
        }
        return "error";

    }

    /**
     * 查询营销参数
     * @param page
     * @param rows
     * @param query
     * @return
     */
    @RequestMapping("querySaleChancesByParams")
    @ResponseBody
    public Map<String, Object> querySaleChancesByParams(@RequestParam(defaultValue = "1") Integer page,
                                                        @RequestParam(defaultValue = "10") Integer rows,
                                                        SaleChanceQuery query){
        query.setPageNum(page);
        query.setPageSize(rows);
        return saleChanceService.queryForPage(query);
    }

    /**
     * 更新或者添加操作
     */
    @ResponseBody
    @RequestMapping("saveOrUpdateSaleChance")
    public ResultInfo saveOrUpdateSaleChance(SaleChance saleChance, HttpServletRequest request){
        //获取ID
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        saleChanceService.saveOrUpdateSaleChance(saleChance,userId);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }

    @ResponseBody
    @RequestMapping("queryAllCustomerManager")
    public List<Map> queryAllCustomerManager(){
        return saleChanceService.queryAllCustomerManager();
    }

    @ResponseBody
    @RequestMapping("deleteSaleChanceBatch")
    public ResultInfo deleteSaleChanceBatch(Integer[] ids){
        saleChanceService.deleteBatch(ids);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }

    @ResponseBody
    @RequestMapping("updateSaleChanceDevResult")
    public ResultInfo updateSaleChanceDevResult(SaleChance saleChance){
        saleChanceService.updateSaleChanceDevResult(saleChance);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }


}
