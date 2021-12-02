package com.javapbl.studentcourses.controller;

import java.util.List;

import com.javapbl.studentcourses.entities.AuthRequest;
import com.javapbl.studentcourses.entities.Course;
import com.javapbl.studentcourses.services.CourseService;
import com.javapbl.studentcourses.utils.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Mycontroller {
    @RequestMapping("/")
    public String hello() {
        return "hello";
    }

    @Autowired
    private CourseService courseService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    // get the courses
    @GetMapping("/courses")
    public List<Course> getCourses() {
        return this.courseService.getCourses();
    }

    @GetMapping("/courses/{courseId}")
    public Course getCourse(@PathVariable String courseId) {
        return this.courseService.getCourse(Long.parseLong(courseId));
    }

    @PostMapping("/courses")
    public Course addCourse(@RequestBody Course course) {
        return this.courseService.addCourse(course);
    }

    @PutMapping("/courses")
    public Course updateCourse(@RequestBody Course course) {
        return this.courseService.updateCourse(course);
    }

    @DeleteMapping("/courses/{courseId}")
    public String deleteCourse(@PathVariable Long courseId) {
        return this.courseService.deleteCourse(courseId);
    }

    @PostMapping("/auth")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception("invalid username / Password");
        }
        return jwtUtil.generateToken(authRequest.getUsername());
    }
}
