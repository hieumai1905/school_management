package com.example.school_management.DAOs.semester;

import com.example.school_management.models.Semester;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import java.util.List;

public class SemesterDAO implements ISemesterDAO {

    private SessionFactory sessionFactory;

    public SemesterDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Semester> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Semester> query = session.createQuery("FROM Semester", Semester.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Semester semester) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(semester);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Semester findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Semester.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Semester semester = session.get(Semester.class, id);
            if (semester != null) {
                session.delete(semester);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Long id, Semester semester) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Semester existingSemester = session.get(Semester.class, id);
            if (existingSemester != null) {
                existingSemester.setSemesterName(semester.getSemesterName());
                existingSemester.setStartAt(semester.getStartAt());
                existingSemester.setFinishAt(semester.getFinishAt());
                session.merge(existingSemester);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}