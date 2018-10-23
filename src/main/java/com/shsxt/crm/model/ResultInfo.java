/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ResultInfo
 * Author:   Yuan
 * Date:     2018/10/13 11:46
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.shsxt.crm.model;


/**
 *
 *
 *
 * @author Yuan
 * @create 2018/10/13
 * @since 1.0.0
 */
public class ResultInfo {
    private Integer code = 200;
    private String msg = "操作成功";
    private Object result;

    public Object getResult() {
        return result;
    }

    public ResultInfo(Object result) {
        this.result = result;
    }

    public ResultInfo(Integer code, Object result) {
        this.code = code;
        this.result = result;
    }

    public ResultInfo(String msg, Object result) {
        this.msg = msg;
        this.result = result;
    }

    public ResultInfo(Integer code, String msg, Object result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public ResultInfo(Integer code) {
        this.code = code;
    }

    public ResultInfo(String msg) {
        this.msg = msg;
    }

    public ResultInfo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultInfo() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
