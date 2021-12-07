package com.javapbl.studentcourses.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.javapbl.studentcourses.entities.AuthRequest;
import com.javapbl.studentcourses.entities.Course;
import com.javapbl.studentcourses.services.CourseService;
import com.javapbl.studentcourses.utils.JwtUtil;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class Mycontroller {
    @RequestMapping("/")
    @CrossOrigin(origins = "http://localhost:9999'")
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
    @CrossOrigin(origins = "http://localhost:9999'")
    public List<Course> getCourses() {
        return this.courseService.getCourses();
    }

    @GetMapping("/deleteAll")
    @CrossOrigin(origins = "http://localhost:9999'")
    public String deleteAllCourses() {
        return this.courseService.deleteAllCourses();
    }

    @GetMapping("/courses/{courseId}")
    @CrossOrigin(origins = "http://localhost:9999'")
    public Course getCourse(@PathVariable String courseId) {
        return this.courseService.getCourse(Long.parseLong(courseId));
    }

    @PostMapping("/courses")
    @CrossOrigin(origins = "http://localhost:9999'")
    public Course addCourse(@RequestBody Course course) {
        return this.courseService.addCourse(course);
    }

    @PutMapping("/courses")
    @CrossOrigin(origins = "http://localhost:9999'")
    public Course updateCourse(@RequestBody Course course) {
        return this.courseService.updateCourse(course);
    }

    @DeleteMapping("/courses/{courseId}")
    @CrossOrigin(origins = "http://localhost:9999'")
    public String deleteCourse(@PathVariable Long courseId) {
        return this.courseService.deleteCourse(courseId);
    }

    @PostMapping("/auth")
    @CrossOrigin(origins = "http://localhost:9999")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (Exception e) {
            throw new Exception("invalid username / Password");
        }
        return jwtUtil.generateToken(authRequest.getUsername());
    }

    @RequestMapping(value = { "/auth", "/courses" }, method = RequestMethod.OPTIONS)
    public ResponseEntity options(HttpServletResponse response) {
        response.setHeader("Allow", "HEAD,GET,PUT,OPTIONS");
        return new ResponseEntity(HttpStatus.OK);
    }
}
