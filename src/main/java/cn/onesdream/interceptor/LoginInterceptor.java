package cn.onesdream.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getSession().getAttribute("loginUserName") != null){
            System.out.println("loginUserName:" + request.getSession().getAttribute("loginUserName"));
            return true;
        }
        response.sendRedirect("/user/login");
        return false;
    }
}
