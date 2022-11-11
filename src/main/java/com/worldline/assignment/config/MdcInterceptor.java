package com.worldline.assignment.config;

import com.worldline.assignment.util.OperationsUtil;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * The class MDC Interceptor
 */
public class MdcInterceptor implements HandlerInterceptor {

    /**
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String correlationId = getCorrelationId();
        MDC.put(OperationsUtil.CORRELATION_ID, correlationId);
        request.setAttribute(OperationsUtil.CORRELATION_ID, correlationId);
        return true;
    }

    /**
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove(OperationsUtil.CORRELATION_ID);
    }

    /**
     *
     * @return correlation id - identifier of request and response
     */
    private String getCorrelationId() {
        return UUID.randomUUID().toString();
    }

}
