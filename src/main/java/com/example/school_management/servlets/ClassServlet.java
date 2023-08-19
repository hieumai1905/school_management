package com.example.school_management.servlets;

import com.example.school_management.DAOs.academicyear.AcademicYearDAO;
import com.example.school_management.DAOs.academicyear.IAcademicYearDAO;
import com.example.school_management.DAOs.class_.ClassDAO;
import com.example.school_management.DAOs.class_.IClassDAO;
import com.example.school_management.DAOs.department.DepartmentDAO;
import com.example.school_management.DAOs.department.IDepartmentDAO;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/classes")
public class ClassServlet extends HttpServlet {

    private IClassDAO classDAO;
    private IAcademicYearDAO academicYearDAO;
    private ITeacherDAO teacherDAO;
    private IDepartmentDAO departmentDAO;

    @Override
    public void init() throws ServletException {
        System.out.println("ClassServlet - Init");
        classDAO = new ClassDAO((SessionFactory) getServletContext().getAttribute("sessionFactory"));
        academicYearDAO = new AcademicYearDAO((SessionFactory) getServletContext().getAttribute("sessionFactory"));
        teacherDAO = new TeacherDAO((SessionFactory) getServletContext().getAttribute("sessionFactory"));
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
                showModuleList(req, resp);
                break;
        }
    }

    private void findClass(HttpServletRequest req, HttpServletResponse resp) {
        Long classId = Long.valueOf(req.getParameter("id"));
        Class_ class_ = classDAO.findById(classId);
        String url = "/WEB-INF/views/class/list.jsp";
        List<Class_> classes = new ArrayList<>();
        if (class_ != null)
            classes.add(class_);
        req.setAttribute("classes", classes);
        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showModuleList(HttpServletRequest req, HttpServletResponse resp) {
        redirectTo(req, resp, "/WEB-INF/views/class/list.jsp");
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
            case "find":
                findClass(req, resp);
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
            classDAO.delete(id);
            req.setAttribute("message", "Xoá thành công");

            List<Class_> classs = classDAO.findAll();
            req.setAttribute("classes", classs);
        } catch (Exception e) {
            req.setAttribute("message", "Xoá thất bại");
            System.out.println(e.getMessage());
        }
        redirectTo(req, resp, "/WEB-INF/views/class/list.jsp");
    }

    private void editModule(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            String classroom = req.getParameter("classroom");

            Long departmentId = Long.parseLong(req.getParameter("department-id"));
            Long teacherId = Long.parseLong(req.getParameter("teacher-id"));
            Long academicYearId = Long.parseLong(req.getParameter("academic-year-id"));

            Department department = departmentDAO.findById(departmentId);
            Teacher teacher = teacherDAO.findById(teacherId);
            AcademicYear academicYear = academicYearDAO.findById(academicYearId);
            Class_ class_ = new Class_(id, classroom, teacher, department, academicYear);
            classDAO.update(id, class_);
            req.setAttribute("message", "Cập nhật thành công");
        } catch (Exception e) {
            req.setAttribute("message", "Cập nhật thất bại");
            System.out.println(e.getMessage());
        }
        redirectTo(req, resp, "/WEB-INF/views/class/list.jsp");
    }

    private void addModule(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String classroom = req.getParameter("classroom");

            Long departmentId = Long.parseLong(req.getParameter("department-id"));
            Long teacherId = Long.parseLong(req.getParameter("teacher-id"));
            Long academicYearId = Long.parseLong(req.getParameter("academic-year-id"));

            Department department = departmentDAO.findById(departmentId);
            Teacher teacher = teacherDAO.findById(teacherId);
            AcademicYear academicYear = academicYearDAO.findById(academicYearId);

            Class_ class_ = new Class_(classroom, teacher, department, academicYear);

            classDAO.save(class_);
            req.setAttribute("message", "Thêm thành công");
        } catch (Exception e) {
            req.setAttribute("message", "Thêm thất bại");
        }
        redirectTo(req, resp, "/WEB-INF/views/class/list.jsp");
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("ClassServlet - Destroy");
    }

    private void redirectTo(HttpServletRequest request, HttpServletResponse response, String url) {
        try {
            List<Class_> class_ = classDAO.findAll();
            request.setAttribute("classes", class_);
            List<Teacher> teachers = teacherDAO.findAll();
            request.setAttribute("teachers", teachers);
            List<Department> departments = departmentDAO.findAll();
            request.setAttribute("departments", departments);
            List<AcademicYear> academicYears = academicYearDAO.findAll();
            request.setAttribute("academicYears", academicYears);
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
