package com.javapbl.studentcourses;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import com.javapbl.studentcourses.dao.CourseDao;
import com.javapbl.studentcourses.entities.Course;
import com.javapbl.studentcourses.entities.User;
import com.javapbl.studentcourses.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class StudentCoursesApplication {
	@Autowired
	private UserRepository repository;

	@Autowired
	private CourseDao courseDao;

	@PostConstruct
	public void initUsers() {
		List<User> users = Stream.of(
				new User(101, "javatechie", "password", "javatechie@gmail.com"),
				new User(102, "user1", "pwd1", "user1@gmail.com"),
				new User(102, "test", "test", "test@test.com"),
				new User(103, "user2", "pwd2", "user2@gmail.com"),
				new User(104, "user3", "pwd3", "user3@gmail.com")).collect(Collectors.toList());
		repository.saveAll(users);
	}

	@PostConstruct
	public void initCourses() {
		List<Course> courses = Stream.of(
				new Course(001, "Test Course", "test Description")).collect(Collectors.toList());
		courseDao.saveAll(courses);
	}

	public static void main(String[] args) {
		SpringApplication.run(StudentCoursesApplication.class, args);
	}

}
