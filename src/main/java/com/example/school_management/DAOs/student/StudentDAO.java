package com.example.school_management.DAOs.student;

import com.example.school_management.models.Student;
import com.example.school_management.models.enums.Rank;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements IStudentDAO {

    private SessionFactory sessionFactory;

    public StudentDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Student> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Student> query = session.createQuery("FROM Student", Student.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Student student) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Student.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Student student = session.get(Student.class, id);
            if (student != null) {
                session.delete(student);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Long id, Student student) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Student existingStudent = session.get(Student.class, id);
            if (existingStudent != null) {
                existingStudent.setFullname(student.getFullname());
                existingStudent.setEmail(student.getEmail());
                existingStudent.setBirthday(student.getBirthday());
                existingStudent.setPhone(student.getPhone());
                existingStudent.setCIC(student.getCIC());
                existingStudent.setAddress(student.getAddress());
                existingStudent.setRank_(student.getRank_());
                existingStudent.setSex(student.getSex());
                session.merge(existingStudent);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> findByRank(Rank rank) {
        try (Session session = sessionFactory.openSession()) {
            Query<Student> query = session.createQuery("FROM Student WHERE rank_ = :rank", Student.class);
            query.setParameter("rank", rank);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}