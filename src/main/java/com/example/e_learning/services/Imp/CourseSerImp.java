package com.example.e_learning.services.Imp;

import com.example.e_learning.DTO.Request.CourseDto;
import com.example.e_learning.DTO.Response.CourseResponseDto;
import com.example.e_learning.Mapper.CourseMapper;
import com.example.e_learning.models.Course;
import com.example.e_learning.repositories.CourseRepo;
import com.example.e_learning.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.e_learning.Mapper.CourseMapper.*;

@Service
public class CourseSerImp implements CourseService {



    @Autowired
    CourseRepo courseRepo;


    @Override
    public CourseResponseDto AddCourse(CourseDto courseDto) {
       /* Course course = new Course();
        course.setName(courseDto.getName());
        course.setDescription(courseDto.getDescription());
        course.setCategory(courseDto.getCategory());
        course.setPublished(LocalDateTime.now()); // Set published date automatically
        course.setLevel(courseDto.getLevel()); // Set level from the request
*/

        // Save the course
         Course course=ToEntity(courseDto);
        course.setPublished(LocalDateTime.now());
        Course savedCourse = courseRepo.save(course);

        // Map Course entity to CourseResponseDto
        return ToDto(savedCourse);
    }



    @Override
    public CourseResponseDto UpdateCourse(CourseDto updateCourseDto) {
        Course existingCourse = courseRepo.findById(updateCourseDto.getId())
                .orElseThrow(() -> new RuntimeException("Course not found with id: " ));


        updateCourseDto.setPublished(existingCourse.getPublished());
        existingCourse=ToEntity(updateCourseDto);

        courseRepo.save(existingCourse);

        // ToDto(existingCourse);
        return ToDto(existingCourse);


    }


    @Override
    public void DeleteCourse(Long id) {
        courseRepo.deleteById(id);
    }

    @Override
    public List<CourseResponseDto> getAllCourses() {


        return courseRepo.findAll().stream().map(CourseMapper::ToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CourseResponseDto GetCourseById(Long id) {
        Course course = courseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
        return ToDto(course);
    }



}
