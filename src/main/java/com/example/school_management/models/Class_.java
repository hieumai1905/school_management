package com.example.school_management.models;


import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "LopHoc")
public class Class_ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaLop", nullable = false)
    private Long id;

    @Column(name = "TenLop", nullable = false, length=15)
    private String className;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "class_")
    private Collection<Student> students;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaGiangVien", nullable = false)
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaKhoa", nullable = false)
    private Department department;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaNienKhoa", nullable = false)
    private AcademicYear academicYear;

    public Class_() {
    }

    public Class_(Long id, String className, Teacher teacher, Department department, AcademicYear academicYear) {
        this.id = id;
        this.className = className;
        this.teacher = teacher;
        this.department = department;
        this.academicYear = academicYear;
    }

    public Class_(String className, Teacher teacher, Department department, AcademicYear academicYear) {
        this.className = className;
        this.teacher = teacher;
        this.department = department;
        this.academicYear = academicYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }
}
