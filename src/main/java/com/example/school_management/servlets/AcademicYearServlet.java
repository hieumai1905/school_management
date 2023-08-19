package com.example.school_management.servlets;

import com.example.school_management.DAOs.academicyear.AcademicYearDAO;
import com.example.school_management.DAOs.academicyear.IAcademicYearDAO;
import com.example.school_management.models.AcademicYear;
import org.hibernate.SessionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/AcademicYears")
public class AcademicYearServlet extends HttpServlet {
    private IAcademicYearDAO academicYearDAO;

    @Override
    public void init() throws ServletException {
        System.out.println("AcademicYearServlet - Init");
        academicYearDAO = new AcademicYearDAO((SessionFactory) getServletContext().getAttribute("sessionFactory"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            default:
                showAcademicYearList(req, resp);
                break;
        }
    }

    private void showAcademicYearList(HttpServletRequest req, HttpServletResponse resp) {
        redirectTo(req, resp, "/WEB-INF/views/academic-year/list.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                addAcademicYear(req, resp);
                break;
            case "edit":
                editAcademicYear(req, resp);
                break;
            case "delete":
                deleteAcademicYear(req, resp);
                break;
            default:
                showAcademicYearList(req, resp);
                break;
        }
    }

    private void deleteAcademicYear(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            academicYearDAO.delete(id);
            req.setAttribute("message", "Xoá thành công");
        } catch (Exception e) {
            req.setAttribute("message", "Xoá thất bại");
            System.out.println(e.getMessage());
        }
        List<AcademicYear> academicYears = academicYearDAO.findAll();
        req.setAttribute("academicYears", academicYears);
        redirectTo(req, resp, "/WEB-INF/views/academic-year/list.jsp");
    }

    private void editAcademicYear(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            String academicYearValue = req.getParameter("academic-year");
            AcademicYear academicYear = new AcademicYear(id, academicYearValue);
            academicYearDAO.update(id, academicYear);
            req.setAttribute("message", "Cập nhật thành công");
        } catch (Exception e) {
            req.setAttribute("message", "Cập nhật thất bại");
            System.out.println(e.getMessage());
        }
        redirectTo(req, resp, "/WEB-INF/views/academic-year/list.jsp");
    }

    private void addAcademicYear(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String academicYearValue = req.getParameter("academic-year");
            AcademicYear academicYear = new AcademicYear(academicYearValue);
            academicYearDAO.save(academicYear);
            req.setAttribute("message", "Thêm thành công");
        } catch (Exception e) {
            req.setAttribute("message", "Thêm thất bại");
        }
        redirectTo(req, resp, "/WEB-INF/views/academic-year/list.jsp");
    }


    @Override
    public void destroy() {
        super.destroy();
        System.out.println("AcademicYearServlet - Destroy");
    }

    private void redirectTo(HttpServletRequest request, HttpServletResponse response, String url) {
        try {
            List<AcademicYear> academicYears = academicYearDAO.findAll();
            request.setAttribute("academicYears", academicYears);
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
