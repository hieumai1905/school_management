package com.example.school_management.servlets;

import com.example.school_management.DAOs.class_.ClassDAO;
import com.example.school_management.DAOs.class_.IClassDAO;
import com.example.school_management.DAOs.student.IStudentDAO;
import com.example.school_management.DAOs.student.StudentDAO;
import com.example.school_management.models.Class_;
import com.example.school_management.models.Student;
import com.example.school_management.models.enums.Rank;
import com.example.school_management.models.enums.Sex;
import org.hibernate.SessionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/students")
public class StudentServlet extends HttpServlet {

    private IStudentDAO studentDAO;
    private IClassDAO classDAO;

    @Override
    public void init() throws ServletException {
        System.out.println("StudentServlet - Init");
        studentDAO = new StudentDAO((SessionFactory) getServletContext().getAttribute("sessionFactory"));
        classDAO = new ClassDAO((SessionFactory) getServletContext().getAttribute("sessionFactory"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            default:
                showStudentList(req, resp);
                break;
        }
    }

    private void searchStudentByRank(HttpServletRequest req, HttpServletResponse resp) {
        String rank = req.getParameter("rank");
        String url = "/WEB-INF/views/student/list.jsp";
        List<Student> students = studentDAO.findByRank(Rank.valueOf(rank));
        req.setAttribute("students", students);
        List<Class_> classes = classDAO.findAll();
        req.setAttribute("listClass", classes);
        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void findStudent(HttpServletRequest req, HttpServletResponse resp) {
        Long studentId = Long.valueOf(req.getParameter("id"));
        Student student = studentDAO.findById(studentId);
        String url = "/WEB-INF/views/student/list.jsp";
        List<Student> students = new ArrayList<>();
        if (student != null)
            students.add(student);
        req.setAttribute("students", students);
        List<Class_> classes = classDAO.findAll();
        req.setAttribute("listClass", classes);
        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showStudentList(HttpServletRequest req, HttpServletResponse resp) {
        redirectTo(req, resp, "/WEB-INF/views/student/list.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                addStudent(req, resp);
                break;
            case "search":
                searchStudentByRank(req, resp);
                break;
            case "find":
                findStudent(req, resp);
                break;
            case "edit":
                editStudent(req, resp);
                break;
            case "delete":
                deleteStudent(req, resp);
                break;
            default:
                showStudentList(req, resp);
                break;
        }
    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            studentDAO.delete(id);
            req.setAttribute("message", "Xoá thành công");

        } catch (Exception e) {
            req.setAttribute("message", "Xoá thất bại");
            System.out.println(e.getMessage());
        }
        redirectTo(req, resp, "/WEB-INF/views/student/list.jsp");
    }

    private void editStudent(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            String studentName = req.getParameter("fullname");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            Date birthDay = Date.valueOf(req.getParameter("birth-day"));
            String CIC = req.getParameter("CIC");
            String address = req.getParameter("address");
            Sex sex = Sex.valueOf(req.getParameter("sex"));
            Rank rank = Rank.valueOf(req.getParameter("rank"));
            Class_ class_ = new Class_();
            class_.setId(Long.parseLong(req.getParameter("cid")));
            Student student = new Student(id, studentName, email, birthDay, phone, CIC, address, rank, sex);
            student.setClass_(class_);
            studentDAO.update(id, student);
            req.setAttribute("message", "Cập nhật thành công");
        } catch (Exception e) {
            req.setAttribute("message", "Cập nhật thất bại");
            System.out.println(e.getMessage());
        }
        redirectTo(req, resp, "/WEB-INF/views/student/list.jsp");
    }

    private void addStudent(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String studentName = req.getParameter("fullname");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            Date birthDay = Date.valueOf(req.getParameter("birth-day"));
            String CIC = req.getParameter("CIC");
            String address = req.getParameter("address");
            Sex sex = Sex.valueOf(req.getParameter("sex"));
            Rank rank = Rank.valueOf(req.getParameter("rank"));
            Class_ class_ = new Class_();
            class_.setId(Long.parseLong(req.getParameter("cid")));
            Student student = new Student(studentName, email, birthDay, phone, CIC, address, rank, sex);
            student.setClass_(class_);
            studentDAO.save(student);
            req.setAttribute("message", "Thêm thành công");
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
        }
        redirectTo(req, resp, "/WEB-INF/views/student/list.jsp");
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("StudentServlet - Destroy");
    }

    private void redirectTo(HttpServletRequest request, HttpServletResponse response, String url) {
        try {
            List<Class_> listClass = classDAO.findAll();
            request.setAttribute("listClass", listClass);
            List<Student> students = studentDAO.findAll();
            request.setAttribute("students", students);
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
