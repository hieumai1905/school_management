package com.example.school_management.servlets;

import com.example.school_management.DAOs.subject.ISubjectDAO;
import com.example.school_management.DAOs.subject.SubjectDAO;
import com.example.school_management.models.Student;
import com.example.school_management.models.Subject;
import org.hibernate.SessionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/subjects")
public class SubjectServlet extends HttpServlet {
    private ISubjectDAO subjectDAO;

    @Override
    public void init() throws ServletException {
        System.out.println("SubjectServlet - Init");
        subjectDAO = new SubjectDAO((SessionFactory) getServletContext().getAttribute("sessionFactory"));
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

    private void findSubject(HttpServletRequest req, HttpServletResponse resp) {
        Long subjectId = Long.valueOf(req.getParameter("id"));
        Subject subject = subjectDAO.findById(subjectId);
        List<Subject> subjects = new ArrayList<>();
        if (subject != null)
            subjects.add(subject);
        String url = "/WEB-INF/views/subject/list.jsp";
        req.setAttribute("subjects", subjects);
        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showListSubject(HttpServletRequest req, HttpServletResponse resp) {
        redirectTo(req, resp, "/WEB-INF/views/subject/list.jsp");
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
            case "find":
                findSubject(req, resp);
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
            subjectDAO.delete(id);
            req.setAttribute("message", "Xoá thành công");
        } catch (Exception e) {
            req.setAttribute("message", "Xoá thất bại");
            System.out.println(e.getMessage());
        }
        redirectTo(req, resp, "/WEB-INF/views/subject/list.jsp");
    }

    private void editSubject(HttpServletRequest req, HttpServletResponse resp) {
        try {

            Long id = Long.parseLong(req.getParameter("id"));
            String subjectName = req.getParameter("subject-name");
            Integer credit = Integer.parseInt(req.getParameter("credit"));
            String description = req.getParameter("description");
            Subject subject = new Subject(id, subjectName, credit, description);
            subjectDAO.update(id, subject);
            req.setAttribute("message", "Cập nhật thành công");
        } catch (Exception e) {
            req.setAttribute("message", "Cập nhật thất bại");
            System.out.println(e.getMessage());
        }
        redirectTo(req, resp, "/WEB-INF/views/subject/list.jsp");
    }

    private void addSubject(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String subjectName = req.getParameter("subject-name");
            Integer credit = Integer.parseInt(req.getParameter("credit"));
            String description = req.getParameter("description");
            Subject subject = new Subject(subjectName, credit, description);

            subjectDAO.save(subject);
            req.setAttribute("message", "Thêm thành công");
        } catch (Exception e) {
            req.setAttribute("message", "Thêm thất bại");
        }
        redirectTo(req, resp, "/WEB-INF/views/subject/list.jsp");
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("SubjectServlet - Destroy");
    }

    private void redirectTo(HttpServletRequest request, HttpServletResponse response, String url) {
        try {
            List<Subject> subjects = subjectDAO.findAll();
            request.setAttribute("subjects", subjects);
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
