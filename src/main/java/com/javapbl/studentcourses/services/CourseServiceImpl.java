package com.javapbl.studentcourses.services;

import java.util.ArrayList;
import java.util.List;

import com.javapbl.studentcourses.entities.Course;

import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    List<Course> list;

    public CourseServiceImpl() {
        list = new ArrayList<>();
        list.add(new Course(145, "Java", "For testing purposes"));
        list.add(new Course(150, "CURD", "Spring BOOT Testing"));
    }

    @Override
    public List<Course> getCourses() {
        return list;
    }

    @Override
    public Course getCourse(long courseId) {
        Course c = null;
        for (Course course : list) {
            if (course.getId() == courseId) {
                c = course;
                break;
            }
        }
        return c;
    }

    @Override
    public Course addCourse(Course course) {
        list.add(course);
        return course;
    }

    @Override
    public Course updateCourse(Course course) {
        for (Course courses : list) {
            if (courses.getId() == course.getId()) {
                int index = list.indexOf(courses);
                list.set(index, course);
                break;
            }
        }
        return course;
    }

    @Override
    public String deleteCourse(Long courseId) {
        String returnMessage = null;
        for (Course course : list) {
            if (course.getId() == courseId) {
                int index = list.indexOf(course);
                list.remove(index);
                returnMessage = "Successfully Removed";
                break;
            }
        }
        return returnMessage;
    }
}
