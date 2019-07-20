package am.gitc.trello.demo.interceptor;

import am.gitc.trello.demo.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class LogInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, Object> logs = new HashMap<>();
        logs.put("request_time", System.currentTimeMillis());
        logs.put("request_uri", request.getRequestURI());
        request.setAttribute("LOG", logs);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Map<String, Object> logs = (Map<String, Object>) request.getAttribute("LOG");
        long respTime = System.currentTimeMillis();
        logs.put("response_time", respTime);
        logs.put("response_duration", respTime - (Long) logs.get("request_time"));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Map<String, Object> logs = (Map<String, Object>) request.getAttribute("LOG");
        long respTime = System.currentTimeMillis();
        logs.put("response_time_after", respTime);
        logs.put("response_time_after_duration", respTime - (Long) logs.get("request_time"));
        LOGGER.info(JsonUtil.serialize(logs));
    }
}
