package com.oracle.ddbookmarket.uitl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AdminFilter",urlPatterns = "/*")
public class AdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request= (HttpServletRequest) req;
        HttpServletResponse response= (HttpServletResponse) resp;
        //如果不写客户端就被拦截
        if (request.getRequestURI().endsWith("login.jsp") || request.getRequestURI().contains("/bower_components/") || request.getRequestURI().contains("/vcode.png") || request.getRequestURI().endsWith("login")){
            chain.doFilter(req,resp);
            return;
        }
        if (request.getSession().getAttribute("logined")==null || !(boolean)request.getSession().getAttribute("logined") ){
        response.sendRedirect("login.jsp");
        return;
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
