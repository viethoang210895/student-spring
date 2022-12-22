package com.codegym.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Classes(String name) {
        this.name = name;
    }

    public Classes() {
    }

    @OneToMany(targetEntity = Student.class)
    private List<Student> students;

}
