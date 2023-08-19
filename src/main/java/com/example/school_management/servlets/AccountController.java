package com.example.school_management.servlets;

import com.example.school_management.DAOs.account.AccountDAO;
import com.example.school_management.DAOs.account.IAccountDAO;
import com.example.school_management.models.Account;
import org.hibernate.SessionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/school-management/*")
public class AccountController extends HttpServlet {
    private IAccountDAO accountDAO;

    @Override
    public void init() throws ServletException {
        System.out.println("AccountServlet - Init");
        SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
        accountDAO = new AccountDAO(sessionFactory);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "register":
                showFormRegister(req, resp);
                break;
            case "/logout":
                logoutAccount(req, resp);
                break;
            default:
                showFormLogin(req, resp);
                break;
        }
    }

    private void logoutAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("account", null);

        // Thêm HTTP Header để ngăn trang được lưu vào bộ nhớ cache của trình duyệt
        resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Expires", "0");

        resp.sendRedirect("login.jsp");
    }

    private void showFormLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        redirectTo(req, resp, "/login.jsp");
    }

    private void showFormRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        redirectTo(req, resp, "/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "/register":
                registerAccount(req, resp);
                break;
            default:
                loginAccount(req, resp);
                break;
        }
    }

    private void loginAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String email = req.getParameter("username");
        String password = req.getParameter("password");
        Account account = new Account(email, password);
        boolean loginAccount = accountDAO.login(account);
        req.getSession().setAttribute("account", account);
        if (loginAccount) {
            // Đăng nhập thành công, thực hiện hành động mong muốn
            // Ví dụ: Chuyển hướng đến trang chính
            resp.sendRedirect("/subjects");
        } else {
            // Đăng nhập thất bại, hiển thị thông báo lỗi
            req.setAttribute("error", "Đăng nhập thất bại");
            showFormLogin(req, resp);
        }
    }

    private void registerAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("username");
        String password = req.getParameter("password");
        String rePassword = req.getParameter("repassword");
        if (password.equals(rePassword)) {
            Account account = new Account(email, password);
            boolean registerAccount = false;
            try {
                registerAccount = accountDAO.register(account);
                if (registerAccount) {
                    // Đăng ký thành công, thực hiện hành động mong muốn
                    // Ví dụ: Chuyển hướng đến trang đăng nhập
                    resp.sendRedirect("/school-management/login.jsp");
                } else {
                    // Đăng ký thất bại, hiển thị thông báo lỗi
                    req.setAttribute("error", "Đăng ký thất bại");
                    showFormRegister(req, resp);
                }
            } catch (Exception e) {
                req.setAttribute("error", "Tài khoản đã tồn tại");
                showFormRegister(req, resp);
            }
        } else {
            // Mật khẩu không khớp, hiển thị thông báo lỗi
            req.setAttribute("error", "Mật khẩu không khớp");
            showFormRegister(req, resp);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("AccountServlet - Destroy");
    }

    private void redirectTo(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}