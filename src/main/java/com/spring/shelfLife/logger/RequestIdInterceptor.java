package com.spring.shelfLife.logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
public class RequestIdInterceptor implements HandlerInterceptor {

    @Autowired
    private RequestIdLogger httpRequestIdLogger;

    public static final String REQUEST_ID_HEADER = "X-Request-Id";
    public static final String MDC_KEY_REQUEST_ID = "requestId";
    public static final String START_TIME_ATTR = "startTime";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestId = request.getHeader(REQUEST_ID_HEADER);

        if (requestId == null || requestId.isBlank()) {
            requestId = UUID.randomUUID().toString();
        }

        MDC.put(MDC_KEY_REQUEST_ID, requestId);
        response.setHeader(REQUEST_ID_HEADER, requestId);
        request.setAttribute(START_TIME_ATTR, System.currentTimeMillis());
        httpRequestIdLogger.logInboundRequest(request);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
       try{
           httpRequestIdLogger.logOutboundRequest(request,response,ex);
       }finally{
           MDC.clear();
       }
    }
}
