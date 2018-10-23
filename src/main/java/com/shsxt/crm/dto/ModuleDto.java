/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ModuleDto
 * Author:   Yuan
 * Date:     2018/10/19 11:51
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.shsxt.crm.dto;


/**
 *id、pId、name、checked
 *
 *
 * @author Yuan
 * @create 2018/10/19
 * @since 1.0.0
 */
public class ModuleDto {
    private Integer id;
    private String name;
    private Integer pId;
    private boolean checked = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
