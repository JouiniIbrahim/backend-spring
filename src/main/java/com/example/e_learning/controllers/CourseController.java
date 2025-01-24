package com.example.e_learning.controllers;


import com.example.e_learning.DTO.Request.CourseDto;
import com.example.e_learning.DTO.Response.CourseResponseDto;
import com.example.e_learning.models.Course;
import com.example.e_learning.repositories.CourseRepo;
import com.example.e_learning.services.Imp.CourseSerImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Course")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseController {

@Autowired
private CourseSerImp courseSerImp;



@PostMapping("/AddCourse")
public CourseResponseDto AddCourse(@Valid @RequestBody CourseDto courseDto) {
    return courseSerImp.AddCourse(courseDto);
}

@GetMapping("/AllCourses")
    public List<CourseResponseDto> getAllCourses()
{
    return courseSerImp.getAllCourses();
}

@GetMapping("/OneCourse/{id}")
 public CourseResponseDto GetOneCourse(@PathVariable Long id)
 {
     return courseSerImp.GetCourseById(id);
 }

 @DeleteMapping("/DeleteCourse/{id}")
 public void deleteCourse(@PathVariable Long id) {  courseSerImp.DeleteCourse(id); }

    @PutMapping("/UpdateCourse")
    public CourseResponseDto UpdateCourse( @Valid @RequestBody CourseDto updateCourseDto) {
        System.out.println("----------------------------");
        return courseSerImp.UpdateCourse(updateCourseDto);

    }

}
