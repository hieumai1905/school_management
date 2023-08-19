package com.example.school_management.models;

import javax.persistence.*;

@Table(name = "TaiKhoan")
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaTaiKhoan", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "MatKhau", nullable = false, length = 50)
    private String password;

    @Transient
    private String rePassword;

    public Account() {
    }

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Account(String email, String password, String rePassword) {
        this.email = email;
        this.password = password;
        this.rePassword = rePassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
}
