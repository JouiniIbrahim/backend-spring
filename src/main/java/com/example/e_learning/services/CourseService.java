package com.example.e_learning.services;

import com.example.e_learning.DTO.Request.CourseDto;
import com.example.e_learning.DTO.Request.CourseWithFileDto;
import com.example.e_learning.DTO.Response.CourseResponseDto;

import java.util.List;

public interface CourseService {
    public CourseResponseDto AddCourse(CourseWithFileDto courseWithFileDto);
    public CourseResponseDto UpdateCourse(CourseWithFileDto courseWithFileDto);
    public void DeleteCourse(Long id  );
    public List<CourseResponseDto> getAllCourses();
    public CourseResponseDto GetCourseById(Long id);

    interface InstructorService {
    }
}
