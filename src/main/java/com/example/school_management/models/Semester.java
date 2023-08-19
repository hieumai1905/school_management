package com.example.school_management.models;


import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "HocKy")
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaHocKy", nullable = false)
    private Long id;

    @Column(name = "HocKy", nullable = false, length=30)
    private String semesterName;

    @Column(name = "NamBatDau", nullable = false, length = 30)
    private String startAt;

    @Column(name = "NamKetThuc", nullable = false, length = 30)
    private String finishAt;

    @OneToMany(mappedBy = "semester")
    private Collection<Module_> module;

    public Semester() {
    }

    public Semester(Long id, String semesterName, String startAt, String finishAt) {
        this.id = id;
        this.semesterName = semesterName;
        this.startAt = startAt;
        this.finishAt = finishAt;
    }

    public Semester(String semesterName, String startAt, String finishAt) {
        this.semesterName = semesterName;
        this.startAt = startAt;
        this.finishAt = finishAt;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getFinishAt() {
        return finishAt;
    }

    public void setFinishAt(String finishAt) {
        this.finishAt = finishAt;
    }

    public Collection<Module_> getModule() {
        return module;
    }

    public void setModule(Collection<Module_> module) {
        this.module = module;
    }
}
