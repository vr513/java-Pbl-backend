package com.javapbl.studentcourses.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Course {

    @Id
    private long id;
    private String title;
    private String Description;

    public Course(long id, String title, String Description) {

        this.setTitle(title);
        this.setId(id);
        this.setDescription(Description);
    }

    public Course() {
        super();
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
