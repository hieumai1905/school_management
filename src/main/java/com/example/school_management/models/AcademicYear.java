package com.example.school_management.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "NienKhoa")
public class AcademicYear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaNienKhoa", nullable = false)
    private Long id;

    @Column(name = "NienKhoa", nullable = false, length = 10)
    private String academicYear;

    @OneToMany(mappedBy = "academicYear", fetch = FetchType.LAZY)
    private Collection<Class_> class_;

    public AcademicYear() {
    }

    public AcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public AcademicYear(Long id, String academicYear) {
        this.id = id;
        this.academicYear = academicYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public Collection<Class_> getClass_() {
        return class_;
    }

    public void setClass_(Collection<Class_> class_) {
        this.class_ = class_;
    }
}
