package com.example.school_management.models;

import com.example.school_management.models.enums.Level;
import com.example.school_management.models.enums.Sex;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "GiangVien")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaGiangVien", nullable = false)
    private Long id;

    @Column(name = "TenGiangVien", nullable = false, length=100)
    private String teacherName;

    @Column(name = "GioiTinh", nullable = false, length=10)
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(name = "SoDienThoai", nullable = false, length=10)
    private String phone;

    @Column(name = "Email", nullable = false, length = 100)
    private String email;

    @Column(name = "DiaChi", nullable = false)
    private String address;

    @Column(name = "TrinhDoHocVan", nullable = false, length=10)
    @Enumerated(EnumType.STRING)
    private Level level;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
    private Collection<Module_> modules;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
    private Collection<Class_> classes;

    public Teacher() {
    }

    public Teacher(Long id, String teacherName, Sex sex, String phone, String email, String address, Level level) {
        this.id = id;
        this.teacherName = teacherName;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.level = level;
    }

    public Teacher(String teacherName, Sex sex, String phone, String email, String address, Level level) {
        this.teacherName = teacherName;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Collection<Module_> getModules() {
        return modules;
    }

    public void setModules(Collection<Module_> modules) {
        this.modules = modules;
    }

    public Collection<Class_> getClasses() {
        return classes;
    }

    public void setClasses(Collection<Class_> classes) {
        this.classes = classes;
    }
}
