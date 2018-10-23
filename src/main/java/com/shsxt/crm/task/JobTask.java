/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: JobTask
 * Author:   Yuan
 * Date:     2018/10/23 9:09
 * Description: 定时任务
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.shsxt.crm.task;


import com.shsxt.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * 定时任务
 *
 * @author Yuan
 * @create 2018/10/23
 * @since 1.0.0
 */
//@Component
public class JobTask {

    @Autowired
    private CustomerService customerService;

    @Scheduled(cron = "0/5 * * * * ? ")
    public void task1(){
        System.out.println("============开始定时任务============");
        customerService.addLossCustomers();
        System.out.println("============结束定时任务============");
    }
}
