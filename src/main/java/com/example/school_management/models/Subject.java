package com.example.school_management.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "MonHoc")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaMonHoc", nullable = false)
    private Long id;

    @Column(name = "TenMonHoc", nullable = false, length = 100)
    private String subjectName;

    @Column(name = "SoTinChi", nullable = false)
    private Integer credit;

    @Column(name = "MoTa", nullable = false)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subject")
    private Collection<Module_> modules;

    public Subject() {
    }

    public Subject(Long id, String subjectName, Integer credit, String description) {
        this.id = id;
        this.subjectName = subjectName;
        this.credit = credit;
        this.description = description;
    }

    public Subject(String subjectName, Integer credit, String description) {
        this.subjectName = subjectName;
        this.credit = credit;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Module_> getModules() {
        return modules;
    }

    public void setModules(Collection<Module_> modules) {
        this.modules = modules;
    }
}
