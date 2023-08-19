package com.example.school_management.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "HocPhan")
public class Module_ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaHocPhan", nullable = false)
    private Long id;

    @Column(name = "Diem")
    private Float score;

    @Column(name = "ThoiGianBatDau", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startAt;

    @Column(name = "ThoiGianKetThuc", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date finishAt;

    @Column(name = "TenPhong", nullable = false, length = 15)
    private String classroom;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaMonHoc", nullable = false)
    private Subject subject;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaHocKy", nullable = false)
    private Semester semester;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaSinhVien", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaGiangVien", nullable = false)
    private Teacher teacher;

    public Module_() {
    }

    public Module_(Float score, Date startAt, Date finishAt, String classroom, Subject subject, Semester semester, Student student, Teacher teacher) {
        this.score = score;
        this.startAt = startAt;
        this.finishAt = finishAt;
        this.classroom = classroom;
        this.subject = subject;
        this.semester = semester;
        this.student = student;
        this.teacher = teacher;
    }

    public Module_(Long id, Float score, Date startAt, Date finishAt, String classroom, Subject subject, Semester semester, Student student, Teacher teacher) {
        this.id = id;
        this.score = score;
        this.startAt = startAt;
        this.finishAt = finishAt;
        this.classroom = classroom;
        this.subject = subject;
        this.semester = semester;
        this.student = student;
        this.teacher = teacher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getFinishAt() {
        return finishAt;
    }

    public void setFinishAt(Date finishAt) {
        this.finishAt = finishAt;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
