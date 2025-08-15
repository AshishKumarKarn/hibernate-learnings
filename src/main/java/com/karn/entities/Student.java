package com.karn.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
@SuppressWarnings("unused")
public class Student{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    @ManyToOne
    private Standard standard;
    private String section;

    @Lob
    private String about;

    public Student() {
        // Default constructor for JPA
    }

    public Student(String name, String email, Standard standard, String section, String about) {
        this.name = name;
        this.email = email;
        this.standard = standard;
        this.section = section;
        this.about = about;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Standard getStd() {
        return standard;
    }

    public String getSection() {
        return section;
    }

    public String getAbout() {
        return about;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStandard(Standard standard) {
        this.standard = standard;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", standard=" + standard +
                ", section='" + section + '\'' +
                ", about='" + about + '\'' +
                '}';
    }
}
