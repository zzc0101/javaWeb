package com.zzc.excise.filter;

import com.zzc.excise.dao.ResourceDao;
import com.zzc.excise.vo.Resource;
import com.zzc.excise.vo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class RoleFilter implements Filter {

    private String roleCheckUri;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        String path = request.getServletPath();  // 获取请求的 URL-Pattern地址

        if(roleCheckUri.contains(path)) {
            User user = (User) request.getSession().getAttribute("main");
            ResourceDao resourceDao = new ResourceDao();
            List<Resource> resourceList = resourceDao.getResource(user.getUserName());
            for(Resource resource:resourceList) {
                if(resource.getUrl().equals(path)) {
                    chain.doFilter(req,resp);
                    break;
                }
            }
                request.setAttribute("error","抱歉，当前用户没有访问该资源的权限!!");
                request.setAttribute("role","true");
                request.getRequestDispatcher("/error.jsp").forward(request,resp);
        } else {
            chain.doFilter(req,resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        roleCheckUri = config.getInitParameter("roleCheckUri");
    }

}
