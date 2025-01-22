package com.example.e_learning.services.Imp;

import com.example.e_learning.models.Course;
import com.example.e_learning.repositories.CourseRepo;
import com.example.e_learning.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class CourseSerImp implements CourseService {
    @Autowired
    CourseRepo courseRepo;


    @Override
    public Course AddCourse(Course course) {
        course.setPublished(LocalDateTime.now());
        return courseRepo.save(course);
    }

    @Override
    public Course UpdateCourse(Course course, Long id) {

        return courseRepo.save(course);
    }

    @Override
    public void DeleteCourse(Long id) {
        courseRepo.deleteById(id); ;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    @Override
    public Course GetCourseById(Long id) {
        return courseRepo.findById(id).orElseThrow();
    }



}
