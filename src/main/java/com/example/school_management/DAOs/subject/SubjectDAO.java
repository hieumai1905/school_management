package com.example.school_management.DAOs.subject;

import com.example.school_management.models.Subject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class SubjectDAO implements ISubjectDAO {

    private SessionFactory sessionFactory;

    public SubjectDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Subject> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Subject> query = session.createQuery("FROM Subject", Subject.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Subject subject) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(subject);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Subject findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Subject.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Subject subject = session.get(Subject.class, id);
            if (subject != null) {
                session.delete(subject);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Long id, Subject subject) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Subject existingSubject = session.get(Subject.class, id);
            if (existingSubject != null) {
                existingSubject.setSubjectName(subject.getSubjectName());
                existingSubject.setCredit(subject.getCredit());
                existingSubject.setDescription(subject.getDescription());
                session.merge(existingSubject);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}