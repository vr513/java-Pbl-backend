package com.javapbl.studentcourses.services;

import java.util.List;

import com.javapbl.studentcourses.entities.Course;

public interface CourseService {

    public List<Course> getCourses();

    public Course getCourse(long courseId);

    public Course addCourse(Course course);

    public Course updateCourse(Course course);

    public String deleteCourse(Long courseId);
}
