/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: RequestPermission
 * Author:   Yuan
 * Date:     2018/10/21 8:55
 * Description: 后台权限
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.shsxt.crm.annotations;


import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 *
 * 后台权限
 *
 * @author Yuan
 * @create 2018/10/21
 * @since 1.0.0s
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestPermission {
    String aclValue() default "";
}
