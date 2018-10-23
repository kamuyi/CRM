/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: IndexController
 * Author:   Yuan
 * Date:     2018/10/13 10:16
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.shsxt.crm.controller;


import com.shsxt.crm.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 *
 *
 *
 * @author Yuan
 * @create 2018/10/13
 * @since 1.0.0
 */
@Controller
public class IndexController extends BaseController {

    @RequestMapping("index")
    public String index(HttpServletRequest request){
        return "index";
    }
}

