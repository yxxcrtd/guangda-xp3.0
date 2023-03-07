/*
 * 文件名：SSOCookieOperateor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年7月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.framework.sso;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 从Cookie值里取出SSOUSERNAME的处理接口
 *
 * @version 1.0 2018年7月18日
 * @see SSOCookieOperateor
 * @since 1.0
 * @since 1.0.1 增加HttpServletResponse参数
 */
public interface SSOCookieOperateor {
    /**
     * 从Cookie或Session里取出SSO用户名
     * @param httpRequest HttpServletRequest
     *
     * @return SSOUSERID
     */
    String getSsoUserName(HttpServletRequest httpRequest);

    // 增加response
    default String getSsoUserName(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        return this.getSsoUserName(httpRequest);
    }

}
