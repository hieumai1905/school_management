package com.example.school_management.models;

import com.example.school_management.models.enums.Rank;
import com.example.school_management.models.enums.Sex;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "SinhVien")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaSinhVien", nullable = false)
    private Long id;

    @Column(name = "HoTen", nullable = false, length = 100)
    private String fullname;

    @Column(name = "Email", nullable = false, length = 100)
    private String email;

    @Column(name = "NgaySinh", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(name = "SoDienThoai", nullable = false, length = 10)
    private String phone;

    @Column(name = "CCCD", nullable = false, length = 12)
    private String CIC;

    @Column(name = "DiaChi", nullable = false, length = 300)
    private String address;

    @Column(name = "XepLoai", nullable = false, length = 11)
    @Enumerated(EnumType.STRING)
    private Rank rank_;

    @Column(name = "GioiTinh", nullable = false, length = 10)
    private Sex sex;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private Collection<Module_> modules;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaLop", nullable = false)
    private Class_ class_;

    public Student() {
    }

    public Student(String fullname, String email, Date birthday, String phone, String CIC, String address, Rank rank_, Sex sex) {
        this.fullname = fullname;
        this.email = email;
        this.birthday = birthday;
        this.phone = phone;
        this.CIC = CIC;
        this.address = address;
        this.rank_ = rank_;
        this.sex = sex;
    }

    public Student(Long id, String fullname, String email, Date birthday, String phone, String CIC, String address, Rank rank_, Sex sex) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.birthday = birthday;
        this.phone = phone;
        this.CIC = CIC;
        this.address = address;
        this.rank_ = rank_;
        this.sex = sex;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCIC() {
        return CIC;
    }

    public void setCIC(String CIC) {
        this.CIC = CIC;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Rank getRank_() {
        return rank_;
    }

    public void setRank_(Rank rank_) {
        this.rank_ = rank_;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Collection<Module_> getModules() {
        return modules;
    }

    public void setModules(Collection<Module_> modules) {
        this.modules = modules;
    }

    public Class_ getClass_() {
        return class_;
    }

    public void setClass_(Class_ class_) {
        this.class_ = class_;
    }
}
