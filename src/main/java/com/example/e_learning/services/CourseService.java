package com.example.e_learning.services;

import com.example.e_learning.DTO.Request.CourseDto;
import com.example.e_learning.DTO.Response.CourseResponseDto;
import com.example.e_learning.models.Course;

import java.util.List;

public interface CourseService {
    public CourseResponseDto AddCourse(CourseDto courseDto);
    public CourseResponseDto UpdateCourse(CourseDto updateCourseDto);
    public void DeleteCourse(Long id  );
    public List<CourseResponseDto> getAllCourses();
    public CourseResponseDto GetCourseById(Long id);

    interface InstructorService {
    }
}
