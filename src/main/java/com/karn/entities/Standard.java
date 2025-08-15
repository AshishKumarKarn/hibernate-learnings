package com.karn.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "standards")
@SuppressWarnings("unused")
public class Standard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String section;

    @OneToMany(mappedBy = "standard", fetch = FetchType.LAZY)
    private List<Student> students = new ArrayList<>();

    public Standard() {
        // Default constructor for JPA
    }

    public Standard(String name, String section) {
        this.name = name;
        this.section = section;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Standard{" + "id=" + id +
                ", name='" + name + '\'' +
                ", section='" + section +
                '}';
    }
}
