package com.example.e_learning.services;

import com.example.e_learning.models.Course;

import java.util.List;

public interface CourseService {
    public Course AddCourse(Course course);
    public Course UpdateCourse(Course course, Long id);
    public void DeleteCourse(Long id);
    public List<Course> getAllCourses();
    public Course GetCourseById(Long id);
}
