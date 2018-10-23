/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: BaseController
 * Author:   Yuan
 * Date:     2018/10/13 17:42
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.shsxt.crm.base;


import com.shsxt.crm.model.ResultInfo;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 *
 *
 *
 * @author Yuan
 * @create 2018/10/13
 * @since 1.0.0
 */
public class BaseController {

    /**当前项目路径
     *
     * @param request
     */
    @ModelAttribute
    public void preHandle(HttpServletRequest request){
        request.setAttribute("ctx",request.getContextPath());
    }

    public ResultInfo success(Integer code,String msg,Object result){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setResult(result);
        resultInfo.setMsg(msg);
        resultInfo.setCode(code);
        return resultInfo;
    }
    public ResultInfo success(Integer code){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(code);
        return resultInfo;
    }
    public ResultInfo success(String msg){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMsg(msg);
        return resultInfo;
    }
    public ResultInfo success(Object result){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setResult(result);
        return resultInfo;
    }
    public ResultInfo success(Integer code,String msg){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMsg(msg);
        resultInfo.setCode(code);
        return resultInfo;
    }
    public ResultInfo success(Integer code,Object result){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setResult(result);
        resultInfo.setCode(code);
        return resultInfo;
    }
    public ResultInfo success(String msg,Object result){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setResult(result);
        resultInfo.setMsg(msg);
        return resultInfo;
    }
}
