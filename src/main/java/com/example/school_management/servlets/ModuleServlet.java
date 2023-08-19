package com.example.school_management.servlets;

import com.example.school_management.DAOs.class_.ClassDAO;
import com.example.school_management.DAOs.class_.IClassDAO;
import com.example.school_management.DAOs.module.IModuleDAO;
import com.example.school_management.DAOs.module.ModuleDAO;
import com.example.school_management.DAOs.semester.ISemesterDAO;
import com.example.school_management.DAOs.semester.SemesterDAO;
import com.example.school_management.DAOs.student.IStudentDAO;
import com.example.school_management.DAOs.student.StudentDAO;
import com.example.school_management.DAOs.subject.ISubjectDAO;
import com.example.school_management.DAOs.subject.SubjectDAO;
import com.example.school_management.DAOs.teacher.ITeacherDAO;
import com.example.school_management.DAOs.teacher.TeacherDAO;
import com.example.school_management.models.*;
import org.hibernate.SessionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(urlPatterns = "/modules")
public class ModuleServlet extends HttpServlet {
    private IModuleDAO moduleDAO;
    private ISubjectDAO subjectDAO;
    private ISemesterDAO semesterDAO;
    private ITeacherDAO teacherDAO;
    private IStudentDAO studentDAO;
    private IClassDAO classDAO;

    @Override
    public void init() throws ServletException {
        System.out.println("ModuleServlet - Init");
        SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
        moduleDAO = new ModuleDAO(sessionFactory);
        subjectDAO = new SubjectDAO(sessionFactory);
        semesterDAO = new SemesterDAO(sessionFactory);
        teacherDAO = new TeacherDAO(sessionFactory);
        studentDAO = new StudentDAO(sessionFactory);
        classDAO = new ClassDAO(sessionFactory);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            default:
                showModuleList(req, resp);
                break;
        }
    }

    private void showModuleList(HttpServletRequest req, HttpServletResponse resp) {
        redirectTo(req, resp, "/WEB-INF/views/module/list.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                addModule(req, resp);
                break;
            case "edit":
                editModule(req, resp);
                break;
            case "delete":
                deleteModule(req, resp);
                break;
            default:
                showModuleList(req, resp);
                break;
        }
    }

    private void deleteModule(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            moduleDAO.delete(id);
            req.setAttribute("message", "Xoá thành công");
        } catch (Exception e) {
            req.setAttribute("message", "Xoá thất bại");
            System.out.println(e.getMessage());
        }
        List<Module_> modules = moduleDAO.findAll();
        req.setAttribute("modules", modules);
        redirectTo(req, resp, "/WEB-INF/views/module/list.jsp");
    }

    private void editModule(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            Float score = Float.parseFloat(req.getParameter("score"));
            Date startAt = Date.valueOf(req.getParameter("start-at"));
            Date finishAt = Date.valueOf(req.getParameter("finish-at"));
            String classroom = req.getParameter("classroom");

            Long subjectId = Long.parseLong(req.getParameter("subject-id"));
            Long semesterId = Long.parseLong(req.getParameter("semester-id"));
            Long teacherId = Long.parseLong(req.getParameter("teacher-id"));
            Long studentId = Long.parseLong(req.getParameter("student-id"));

            Subject subject = subjectDAO.findById(subjectId);
            Semester semester = semesterDAO.findById(semesterId);
            Teacher teacher = teacherDAO.findById(teacherId);
            Student student = studentDAO.findById(studentId);

            Module_ module = new Module_(id, score, startAt, finishAt, classroom, subject, semester, student, teacher);
            moduleDAO.update(id, module);
            req.setAttribute("message", "Cập nhật thành công");
        } catch (Exception e) {
            req.setAttribute("message", "Cập nhật thất bại");
            System.out.println(e.getMessage());
        }
        redirectTo(req, resp, "/WEB-INF/views/module/list.jsp");
    }

    private void addModule(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Float score = Float.parseFloat(req.getParameter("score"));
            Date startAt = Date.valueOf(req.getParameter("start-at"));
            Date finishAt = Date.valueOf(req.getParameter("finish-at"));
            String classroom = req.getParameter("classroom");

            Long subjectId = Long.parseLong(req.getParameter("subject-id"));
            Long semesterId = Long.parseLong(req.getParameter("semester-id"));
            Long teacherId = Long.parseLong(req.getParameter("teacher-id"));
            Long studentId = Long.parseLong(req.getParameter("student-id"));

            Subject subject = subjectDAO.findById(subjectId);
            Semester semester = semesterDAO.findById(semesterId);
            Teacher teacher = teacherDAO.findById(teacherId);
            Student student = studentDAO.findById(studentId);
            Module_ module = new Module_(score, startAt, finishAt, classroom, subject, semester, student, teacher);

            moduleDAO.save(module);
            req.setAttribute("message", "Thêm thành công");
        } catch (Exception e) {
            req.setAttribute("message", "Thêm thất bại");
        }
        redirectTo(req, resp, "/WEB-INF/views/module/list.jsp");
    }


    @Override
    public void destroy() {
        super.destroy();
        System.out.println("ModuleServlet - Destroy");
    }

    private void redirectTo(HttpServletRequest request, HttpServletResponse response, String url) {
        try {
            List<Module_> modules = moduleDAO.findAll();
            request.setAttribute("modules", modules);
            List<Teacher> teachers = teacherDAO.findAll();
            request.setAttribute("teachers", teachers);
            List<Subject> subjects = subjectDAO.findAll();
            request.setAttribute("subjects", subjects);
            List<Student> students = studentDAO.findAll();
            request.setAttribute("students", students);
            List<Semester> semesters = semesterDAO.findAll();
            request.setAttribute("semesters", semesters);
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
