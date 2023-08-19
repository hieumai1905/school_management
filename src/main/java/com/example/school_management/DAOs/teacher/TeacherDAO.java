package com.example.school_management.DAOs.teacher;

import com.example.school_management.models.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class TeacherDAO implements ITeacherDAO {

    private SessionFactory sessionFactory;

    public TeacherDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Teacher> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Teacher> query = session.createQuery("FROM Teacher", Teacher.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Teacher teacher) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(teacher);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Teacher findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Teacher.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Teacher teacher = session.get(Teacher.class, id);
            if (teacher != null) {
                session.delete(teacher);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Long id, Teacher teacher) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Teacher existingTeacher = session.get(Teacher.class, id);
            if (existingTeacher != null) {
                existingTeacher.setTeacherName(teacher.getTeacherName());
                existingTeacher.setSex(teacher.getSex());
                existingTeacher.setPhone(teacher.getPhone());
                existingTeacher.setEmail(teacher.getEmail());
                existingTeacher.setAddress(teacher.getAddress());
                existingTeacher.setLevel(teacher.getLevel());
                session.merge(existingTeacher);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}