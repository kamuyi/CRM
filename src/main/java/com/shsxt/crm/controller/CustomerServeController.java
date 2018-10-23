/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CustomerServeController
 * Author:   Yuan
 * Date:     2018/10/23 15:28
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.shsxt.crm.controller;


import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.service.CustomerServeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 *
 *
 * @author Yuan
 * @create 2018/10/23
 * @since 1.0.0
 */
@Controller
@RequestMapping("customerServe")
public class CustomerServeController extends BaseController{

    @Autowired
    private CustomerServeService customerServeService;

    @RequestMapping("index/{state}")
    public String index(@PathVariable Integer state){
        if(state==1){
            return "customer_serve_create";
        }else if(state==2){
            return "customer_serve_assign";
        }else if(state==3){
            return "customer_serve_proce";
        }else if(state==4){
            return "customer_serve_feed_back";
        }else if(state==5){
            return "customer_serve_archive";
        }
        return "error";
    }
}
