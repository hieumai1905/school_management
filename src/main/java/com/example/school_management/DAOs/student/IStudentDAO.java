package com.example.school_management.DAOs.student;

import com.example.school_management.DAOs.IGeneralDAO;
import com.example.school_management.models.Student;
import com.example.school_management.models.enums.Rank;

import java.util.List;

public interface IStudentDAO extends IGeneralDAO<Student> {
    List<Student> findByRank(Rank rank);
}
