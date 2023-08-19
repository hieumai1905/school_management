package com.example.school_management.DAOs.department;

import com.example.school_management.models.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import java.util.List;

public class DepartmentDAO implements IDepartmentDAO {

    private SessionFactory sessionFactory;

    public DepartmentDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Department> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Department> query = session.createQuery("FROM Department", Department.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Department department) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(department);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Department findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Department.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Department department = session.get(Department.class, id);
            if (department != null) {
                session.delete(department);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Long id, Department department) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Department existingDepartment = session.get(Department.class, id);
            if (existingDepartment != null) {
                existingDepartment.setDepartmentName(department.getDepartmentName());
                session.merge(existingDepartment);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}