package com.imooc.manager.error;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 自定义错误处理controller
 *
 * @Auther: what
 * @Date: 2018/12/24 10:37
 * @Description:
 */

public class MyErrorController extends BasicErrorController {
    public MyErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties) {
        super(errorAttributes, errorProperties);
    }

    @Override
    protected Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {

        Map<String, Object> attrs = super.getErrorAttributes(request, includeStackTrace);

        attrs.remove("timestamp");
        attrs.remove("status");
        attrs.remove("error");
        attrs.remove("path");
        String errCode = attrs.get("message").toString();
        ErrorEnum errorEnum = ErrorEnum.getByCode(errCode);

        attrs.put("message", errorEnum.getMessage());
        attrs.put("code", errorEnum.getCode());
        attrs.put("canRetry", errorEnum.getCanRetry());
        return attrs;
    }
}
