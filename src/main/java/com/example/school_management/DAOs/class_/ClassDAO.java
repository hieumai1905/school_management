package com.example.school_management.DAOs.class_;

import com.example.school_management.models.Class_;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ClassDAO implements IClassDAO {

    private SessionFactory sessionFactory;

    public ClassDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Class_> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Class_> query = session.createQuery("FROM Class_", Class_.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Class_ class_) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(class_);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Class_ findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Class_.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Class_ class_ = session.get(Class_.class, id);
            if (class_ != null) {
                session.delete(class_);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Long id, Class_ class_) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Class_ existingClass = session.get(Class_.class, id);
            if (existingClass != null) {
                existingClass.setClassName(class_.getClassName());
                existingClass.setStudents(class_.getStudents());
                existingClass.setTeacher(class_.getTeacher());
                existingClass.setDepartment(class_.getDepartment());
                existingClass.setAcademicYear(class_.getAcademicYear());
                session.merge(existingClass);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}