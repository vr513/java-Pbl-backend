package com.javapbl.studentcourses.dao;

import com.javapbl.studentcourses.entities.Course;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseDao extends JpaRepository<Course, Long> {

}
