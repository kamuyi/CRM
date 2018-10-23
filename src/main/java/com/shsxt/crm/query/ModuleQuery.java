/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ModuleQuery
 * Author:   Yuan
 * Date:     2018/10/20 9:21
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.shsxt.crm.query;


import com.shsxt.crm.base.BaseQuery;

/**
 *
 *
 *
 * @author Yuan
 * @create 2018/10/20
 * @since 1.0.0
 */
public class ModuleQuery extends BaseQuery {
    private String moduleName;

    private Integer parentId;

    private Integer grade;

    private String optValue;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getOptValue() {
        return optValue;
    }

    public void setOptValue(String optValue) {
        this.optValue = optValue;
    }
}
