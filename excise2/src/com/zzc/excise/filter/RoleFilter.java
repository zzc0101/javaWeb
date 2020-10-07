package com.zzc.excise.filter;

import com.zzc.excise.vo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RoleFilter implements Filter {

    private String roleCheckUri;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        String path = request.getServletPath();  // 获取请求的 URL-Pattern地址

        if(roleCheckUri.indexOf(path) != -1) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("main");
            if (user.getRole().equals("1")) {
                chain.doFilter(req,resp);
            } else {
                request.setAttribute("error","抱歉，当前用户没有访问该资源的权限!!");
                request.setAttribute("role","true");
                request.getRequestDispatcher("/error.jsp").forward(request,resp);
            }
        } else {
            chain.doFilter(req,resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        roleCheckUri = config.getInitParameter("roleCheckUri");
    }

}
