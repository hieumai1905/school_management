package com.example.school_management.servlets;

import com.example.school_management.DAOs.department.DepartmentDAO;
import com.example.school_management.DAOs.department.IDepartmentDAO;
import com.example.school_management.models.Department;
import org.hibernate.SessionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/departments")
public class DepartmentServlet extends HttpServlet {
    private IDepartmentDAO departmentDAO;

    @Override
    public void init() throws ServletException {
        System.out.println("DepartmentServlet - Init");
        departmentDAO = new DepartmentDAO((SessionFactory) getServletContext().getAttribute("sessionFactory"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            default:
                showListSubject(req, resp);
                break;
        }
    }

    private void showListSubject(HttpServletRequest req, HttpServletResponse resp) {
        redirectTo(req, resp, "/WEB-INF/views/department/list.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                addSubject(req, resp);
                break;
            case "edit":
                editSubject(req, resp);
                break;
            case "delete":
                deleteSubject(req, resp);
                break;
            default:
                showListSubject(req, resp);
                break;
        }
    }

    private void deleteSubject(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            departmentDAO.delete(id);
            req.setAttribute("message", "Xoá thành công");
        } catch (Exception e) {
            req.setAttribute("message", "Xoá thất bại");
            System.out.println(e.getMessage());
        }
        redirectTo(req, resp, "/WEB-INF/views/department/list.jsp");
    }

    private void editSubject(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            String departmentName = req.getParameter("department-name");
            Department department = new Department(id, departmentName);
            departmentDAO.update(id, department);
            req.setAttribute("message", "Cập nhật thành công");
        } catch (Exception e) {
            req.setAttribute("message", "Cập nhật thất bại");
            System.out.println(e.getMessage());
        }
        redirectTo(req, resp, "/WEB-INF/views/department/list.jsp");
    }

    private void addSubject(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String departmentName = req.getParameter("department-name");
            Department department = new Department(departmentName);
            departmentDAO.save(department);
            req.setAttribute("message", "Thêm thành công");
        } catch (Exception e) {
            req.setAttribute("message", "Thêm thất bại");
        }
        redirectTo(req, resp, "/WEB-INF/views/department/list.jsp");
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("DepartmentServlet - Destroy");
    }

    private void redirectTo(HttpServletRequest request, HttpServletResponse response, String url) {
        try {
            List<Department> departments = departmentDAO.findAll();
            request.setAttribute("departments", departments);
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
