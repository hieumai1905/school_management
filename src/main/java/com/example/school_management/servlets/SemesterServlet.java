package com.example.school_management.servlets;

import com.example.school_management.DAOs.semester.ISemesterDAO;
import com.example.school_management.DAOs.semester.SemesterDAO;
import com.example.school_management.models.Semester;
import org.hibernate.SessionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/semesters")
public class SemesterServlet extends HttpServlet {
    private ISemesterDAO semesterDAO;

    @Override
    public void init() throws ServletException {
        System.out.println("SemesterServlet - Init");
        semesterDAO = new SemesterDAO((SessionFactory) getServletContext().getAttribute("sessionFactory"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            default:
                showSemesterList(req, resp);
                break;
        }
    }

    private void showSemesterList(HttpServletRequest req, HttpServletResponse resp) {
        redirectTo(req, resp, "/WEB-INF/views/semester/list.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                addSemester(req, resp);
                break;
            case "edit":
                editSemester(req, resp);
                break;
            case "delete":
                deleteSemester(req, resp);
                break;
            default:
                showSemesterList(req, resp);
                break;
        }
    }

    private void deleteSemester(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            semesterDAO.delete(id);
            req.setAttribute("message", "Xoá thành công");
        } catch (Exception e) {
            req.setAttribute("message", "Xoá thất bại");
            System.out.println(e.getMessage());
        }
        List<Semester> semesters = semesterDAO.findAll();
        req.setAttribute("semesters", semesters);
        redirectTo(req, resp, "/WEB-INF/views/semester/list.jsp");
    }

    private void editSemester(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            String semesterName = req.getParameter("semester-name");
            String startAt = req.getParameter("start-at");
            String finishAt = req.getParameter("finish-at");
            Semester semester = new Semester(id, semesterName, startAt, finishAt);
            semesterDAO.update(id, semester);
            req.setAttribute("message", "Cập nhật thành công");
        } catch (Exception e) {
            req.setAttribute("message", "Cập nhật thất bại");
            System.out.println(e.getMessage());
        }
        redirectTo(req, resp, "/WEB-INF/views/semester/list.jsp");
    }

    private void addSemester(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String semesterName = req.getParameter("semester-name");
            String startAt = req.getParameter("start-at");
            String finishAt = req.getParameter("finish-at");
            Semester semester = new Semester(semesterName, startAt, finishAt);
            semesterDAO.save(semester);
            req.setAttribute("message", "Thêm thành công");
        } catch (Exception e) {
            req.setAttribute("message", "Thêm thất bại");
        }
        redirectTo(req, resp, "/WEB-INF/views/semester/list.jsp");
    }


    @Override
    public void destroy() {
        super.destroy();
        System.out.println("SemesterServlet - Destroy");
    }

    private void redirectTo(HttpServletRequest request, HttpServletResponse response, String url) {
        try {
            List<Semester> semesters = semesterDAO.findAll();
            request.setAttribute("semesters", semesters);
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
