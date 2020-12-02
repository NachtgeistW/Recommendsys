package cn.iwyu.utils;/**
 * Created by Chester on 26/11/2020.
 */

import org.apache.hadoop.yarn.webapp.hamlet.HamletSpec;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName LoginInterceptor
 * @Description
 * @Author XiaoMao
 * @Date 26/11/2020 下午8:35
 * @Version 1.0
 **/

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        HttpSession session = request.getSession();
//        String userName = (String) session.getAttribute("userName");
//        System.out.println(userName);
//        if(userName==null){
//            request.getRequestDispatcher(request.getContextPath()+"/login").forward(request, response);
//            System.out.println(1);
//            return false;
//        }
        return true;
    }

}
