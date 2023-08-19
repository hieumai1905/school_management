package com.example.school_management.DAOs.academicyear;

import com.example.school_management.models.AcademicYear;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class AcademicYearDAO implements IAcademicYearDAO {

    private SessionFactory sessionFactory;

    public AcademicYearDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<AcademicYear> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<AcademicYear> query = session.createQuery("FROM AcademicYear", AcademicYear.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(AcademicYear academicYear) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(academicYear);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public AcademicYear findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(AcademicYear.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            AcademicYear academicYear = session.get(AcademicYear.class, id);
            if (academicYear != null) {
                session.delete(academicYear);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Long id, AcademicYear academicYear) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            AcademicYear existingAcademicYear = session.get(AcademicYear.class, id);
            if (existingAcademicYear != null) {
                existingAcademicYear.setAcademicYear(academicYear.getAcademicYear());
                session.merge(existingAcademicYear);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}