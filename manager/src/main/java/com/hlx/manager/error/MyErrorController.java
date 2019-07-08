package com.hlx.manager.error;

import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class MyErrorController extends BasicErrorController {
    public MyErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorProperties, errorViewResolvers);
    }

    /**
     *
     * "timestamp": 1554469373504,
     *   "status": 500,
     *   "error": "Internal Server Error",
     *   "exception": "java.lang.IllegalArgumentException",
     *   "message": "产品代码不能空",
     *   "path": "/products/addproduct"
     */
    @Override
    protected Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        Map<String, Object> atrrs= super.getErrorAttributes(request, includeStackTrace);
        atrrs.remove("timestamp");
        atrrs.remove("error");
        atrrs.remove("exception");
        atrrs.remove("path");
        String errorCode= (String) atrrs.get("message");
        ErrorEnum errorEnum=ErrorEnum.getByCode(errorCode);
        atrrs.put("code",errorEnum.getCode());
        atrrs.put("message",errorEnum.getMessage());
        atrrs.put("conRetry",errorEnum.getConRetry());
        return atrrs;
    }
}
