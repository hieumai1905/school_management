package com.example.school_management.servlets;

import com.example.school_management.DAOs.teacher.ITeacherDAO;
import com.example.school_management.DAOs.teacher.TeacherDAO;
import com.example.school_management.models.Student;
import com.example.school_management.models.Subject;
import com.example.school_management.models.Teacher;
import com.example.school_management.models.enums.Level;
import com.example.school_management.models.enums.Sex;
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

@WebServlet(urlPatterns = "/teachers")
public class TeacherServlet extends HttpServlet {
    private ITeacherDAO teacherDAO;

    @Override
    public void init() throws ServletException {
        System.out.println("TeacherServlet - Init");
        teacherDAO = new TeacherDAO((SessionFactory) getServletContext().getAttribute("sessionFactory"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "find":
                findTeacher(req, resp);
                break;
            default:
                showTeacherList(req, resp);
                break;
        }
    }

    private void findTeacher(HttpServletRequest req, HttpServletResponse resp) {
        Long teacherId = Long.valueOf(req.getParameter("id"));
        Teacher teacher = teacherDAO.findById(teacherId);
        String url = "/WEB-INF/views/teacher/list.jsp";
        List<Teacher> teachers = new ArrayList<>();
        if (teacher != null)
            teachers.add(teacher);
        req.setAttribute("teachers", teachers);
        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                addTeacher(req, resp);
                break;
            case "edit":
                editTeacher(req, resp);
                break;
            case "find":
                findTeacher(req, resp);
                break;
            case "delete":
                deleteTeacher(req, resp);
                break;
            default:
                showTeacherList(req, resp);
                break;
        }
    }

    private void deleteTeacher(HttpServletRequest req, HttpServletResponse resp) {
        try {

            Long id = Long.parseLong(req.getParameter("id"));
            teacherDAO.delete(id);
            req.setAttribute("message", "Xoá thành công");
        } catch (Exception e) {
            req.setAttribute("message", "Xoá thất bại");
            System.out.println(e.getMessage());
        }
        redirectTo(req, resp, "/WEB-INF/views/teacher/list.jsp");
    }

    private void showTeacherList(HttpServletRequest req, HttpServletResponse resp) {
        redirectTo(req, resp, "/WEB-INF/views/teacher/list.jsp");
    }

    private void editTeacher(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            String teacherName = req.getParameter("teacher-name");
            Sex sex = Sex.valueOf(req.getParameter("sex"));
            String phone = req.getParameter("phone");
            String email = req.getParameter("email");
            String address = req.getParameter("address");
            Level level = Level.valueOf(req.getParameter("level"));
            Teacher teacher = new Teacher(teacherName, sex, phone, email, address, level);
            teacherDAO.update(id, teacher);
            req.setAttribute("message", "Cập nhật thành công");

        } catch (Exception e) {
            req.setAttribute("message", "Cập nhật thất bại");
            System.out.println(e.getMessage());
        }
        redirectTo(req, resp, "/WEB-INF/views/teacher/list.jsp");
    }

    private void addTeacher(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String teacherName = req.getParameter("teacher-name");
            Sex sex = Sex.valueOf(req.getParameter("sex"));
            String phone = req.getParameter("phone");
            String email = req.getParameter("email");
            String address = req.getParameter("address");
            Level level = Level.valueOf(req.getParameter("level"));
            Teacher teacher = new Teacher(teacherName, sex, phone, email, address, level);
            teacherDAO.save(teacher);
            req.setAttribute("message", "Thêm thành công");

        } catch (Exception e) {
            req.setAttribute("message", "Thêm thất bại");
        }
        redirectTo(req, resp, "/WEB-INF/views/teacher/list.jsp");
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("TeacherServlet - Destroy");
    }

    private void redirectTo(HttpServletRequest request, HttpServletResponse response, String url) {
        try {
            List<Teacher> teachers = teacherDAO.findAll();
            request.setAttribute("teachers", teachers);
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
