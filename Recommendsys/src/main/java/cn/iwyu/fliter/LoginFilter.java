package cn.iwyu.fliter;/**
 * Created by Chester on 2/12/2020.
 */

import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName LoginFilter
 * @Description
 * @Author XiaoMao
 * @Date 2/12/2020 下午4:20
 * @Version 1.0
 **/

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String servletPath = request.getServletPath();
        HttpSession session = request.getSession();
        // 没有userName为空就转发到登陆页面
        if (servletPath.equals("/login.jsp")  ) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if(session.getAttribute("userName")==null){
            String contextPath = request.getContextPath();
//            request.getRequestDispatcher(contextPath+"/login.jsp").forward(request,response);
            response.sendRedirect(contextPath+"/login.jsp");
//            response.sendRedirect(request.getContextPath()+"/index.jsp");  //重定向就相当于地址栏输入 也不能随便访问jsp
        }else{
            // 不为空，就继续请求下一级资源（继续访问）
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
