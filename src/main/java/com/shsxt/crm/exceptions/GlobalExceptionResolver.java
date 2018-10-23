/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: GlobalExceptionResolver
 * Author:   Yuan
 * Date:     2018/10/15 9:23
 * Description: 全局异常
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.shsxt.crm.exceptions;


import com.alibaba.fastjson.JSON;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.model.ResultInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 *
 * 全局异常
 *
 * @author Yuan
 * @create 2018/10/15
 * @since 1.0.0
 */
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mv=createDefaultModelAndView(request,ex);

        /**
         * 拦截登录
         */
        if(ex instanceof LoginException){
            mv.addObject("errorMsg",CrmConstant.USER_NOT_LOGIN_MSG);
            mv.setViewName("login_error");
            return mv;
        }

        /***
         * 1. 区分是什么异常
         * 2. 区分是页面请求还是json请求
         */
        //通过ResponseBody区分是页面请求还是json请求
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod= (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            ResponseBody responseBody = method.getAnnotation(ResponseBody.class);
            if(null==responseBody){
                //页面请求
                if(ex instanceof ParamsException){
                    ParamsException p= (ParamsException) ex;
                    mv.addObject("errorMsg",p.getMsg());

                }
            }else {
                //json请求
                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setCode(300);
                resultInfo.setMsg(CrmConstant.OPS_FAILED_MSG);

                if(ex instanceof ParamsException){
                    //参数异常
                    ParamsException p= (ParamsException) ex;
                    resultInfo.setMsg(p.getMsg());
                }

                //使用流将对象转换成JSON
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/json;charset=utf-8");
                PrintWriter pw =null;

                try {
                    pw=response.getWriter();
                    pw.write(JSON.toJSONString(resultInfo));
                    pw.flush();
                    pw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    //如果没有关闭，需要关闭，安全
                    if(null!=pw){
                        pw.close();
                    }
                }
                return  null;

            }

        }
        return mv;
    }

    private ModelAndView createDefaultModelAndView(HttpServletRequest request, Exception ex) {

        ModelAndView modelAndView = new ModelAndView();
        //跳转到错误页面
        modelAndView.setViewName("error");
        //设置错误参数
        modelAndView.addObject("errorCode", CrmConstant.OPS_FAILED_CODE);
        modelAndView.addObject("errorMsg",CrmConstant.OPS_FAILED_MSG);
        //设置ctx，因为是单独的，需要重新设置
        modelAndView.addObject("ctx",request.getContextPath());
        //请求路径
        modelAndView.addObject("uri", request.getRequestURI());
        return  modelAndView;

    }
}
