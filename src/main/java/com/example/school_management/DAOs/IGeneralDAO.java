package com.example.school_management.DAOs;

import java.util.List;

public interface IGeneralDAO<T> {
    List<T> findAll();

    void save(T t);

    T findById(Long id);

    void delete(Long id);

    void update(Long id, T t);
}