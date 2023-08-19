package com.example.school_management.DAOs.module;

import com.example.school_management.models.Module_;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ModuleDAO implements IModuleDAO {

    private SessionFactory sessionFactory;

    public ModuleDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Module_> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Module_> query = session.createQuery("FROM Module_", Module_.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Module_ module) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(module);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Module_ findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Module_.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Module_ module = session.get(Module_.class, id);
            if (module != null) {
                session.delete(module);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Long id, Module_ module) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Module_ existingModule = session.get(Module_.class, id);
            if (existingModule != null) {
                existingModule.setScore(module.getScore());
                existingModule.setStartAt(module.getStartAt());
                existingModule.setFinishAt(module.getFinishAt());
                existingModule.setClassroom(module.getClassroom());
                existingModule.setSubject(module.getSubject());
                existingModule.setSemester(module.getSemester());
                existingModule.setStudent(module.getStudent());
                existingModule.setTeacher(module.getTeacher());
                session.merge(existingModule);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}