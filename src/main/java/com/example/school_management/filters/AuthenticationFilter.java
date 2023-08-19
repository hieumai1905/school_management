package com.example.school_management.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo filter
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        // Kiểm tra đường dẫn yêu cầu
        String path = request.getRequestURI().substring(request.getContextPath().length());
        if (!path.startsWith("/school-management/") && !path.equals("/login.jsp") && !path.equals("/register.jsp")) {
            // Kiểm tra session

            if (session == null || session.getAttribute("account") == null) {
                // Không tồn tại session hoặc không tồn tại đối tượng account trong session
                response.sendRedirect("/login.jsp");
                return;
            }
        }

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        // Hủy filter
    }
}