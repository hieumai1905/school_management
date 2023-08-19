package com.example.school_management.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Khoa")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaKhoa", nullable = false)
    private Long id;

    @Column(name = "TenKhoa", nullable = false, length=100)
    private String departmentName;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private Collection<Class_> class_;

    public Department() {
    }

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public Department(Long id, String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Collection<Class_> getClass_() {
        return class_;
    }

    public void setClass_(Collection<Class_> class_) {
        this.class_ = class_;
    }
}
