package com.example.e_learning.Mapper;

import com.example.e_learning.DTO.Request.CourseDto;
import com.example.e_learning.DTO.Response.CourseResponseDto;
import com.example.e_learning.models.Course;




public class CourseMapper {
    public static   CourseResponseDto ToDto(Course course) {

        CourseResponseDto responseDto = new CourseResponseDto();
        responseDto.setId(course.getId());
        responseDto.setName(course.getName());
        responseDto.setDescription(course.getDescription());
        responseDto.setCategory(course.getCategory());
        responseDto.setPublished(course.getPublished());
        responseDto.setLevel(course.getLevel());
        return responseDto;


    }

    public static   Course ToEntity(CourseDto courseDto) {

        Course course = new Course();
       course.setId(courseDto.getId());
        course.setName(courseDto.getName());
        course.setDescription(courseDto.getDescription());
        course.setCategory(courseDto.getCategory());

        course.setLevel(courseDto.getLevel());
        return course;
    }





}
