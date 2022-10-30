package glofox.task.interviewTask.interceptors;

import org.aopalliance.intercept.Interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//Still not sure about this. Involves some things I don't fully master/understand

public class LogInterceptor implements HandlerInterceptor {
    private final Logger log = LoggerFactory.getLogger("RequestInterceptor");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(request.getMethod() + " " + request.getRequestURI());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
