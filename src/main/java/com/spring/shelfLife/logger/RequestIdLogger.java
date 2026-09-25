package com.spring.shelfLife.logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RequestIdLogger {
    public static final Logger LOGGER = LoggerFactory.getLogger(RequestIdLogger.class);
    public static final String START_TIME_ATTR = "startTime";

    public void logInboundRequest(HttpServletRequest request) {
        LOGGER.info("Inbound Request: [{}] {} from IP: {}" ,
                request.getMethod(),
                request.getRequestURI(),
                request.getRemoteAddr());
    }

    public void logOutboundRequest(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        Long startTime = (Long) request.getAttribute(START_TIME_ATTR);
        long duration = (startTime!=null)? System.currentTimeMillis() - startTime : 0;

        if(exception!=null){
            LOGGER.error("Outbound Response : Status {} | Duration : {}ms | Exception : {}",
                    response.getStatus(),
                    duration,
                    exception.getMessage(),
                    exception);
        }else{
            LOGGER.info("Outbound Response : Status {} | Duration : {}ms ",
                    response.getStatus(),
                    duration
                    );
        }
    }


}
