package com.example.e_learning.Mapper;

import com.example.e_learning.DTO.Request.CourseDto;
import com.example.e_learning.DTO.Response.CourseResponseDto;
import com.example.e_learning.domain.Course;
import com.example.e_learning.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;


public class CourseMapper {
    public static   CourseResponseDto ToDto(Course course) {

        CourseResponseDto responseDto = new CourseResponseDto();
        responseDto.setId(course.getId());
        responseDto.setName(course.getName());
        responseDto.setDescription(course.getDescription());
        responseDto.setCategory(course.getCategory());
        responseDto.setPublished(course.getPublished());
        responseDto.setLevel(course.getLevel());

        if (course.getFile() != null) {
            responseDto.setFileName(course.getFile().getName());
            responseDto.setFileExtension(course.getFile().getExtension());
        }


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
