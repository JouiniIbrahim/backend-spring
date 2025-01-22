package com.example.e_learning.controllers;


import com.example.e_learning.models.Course;
import com.example.e_learning.repositories.CourseRepo;
import com.example.e_learning.services.Imp.CourseSerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/Course")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseController {

@Autowired
private CourseSerImp courseSerImp;

@Autowired
    private CourseRepo courseRepo;

@PostMapping("/AddCourse")
public Course addCourse( @RequestBody Course course)
{
 return courseSerImp.AddCourse(course);
}

@GetMapping("/AllCourses")
    public List<Course> getAllCourses()
{
    return courseSerImp.getAllCourses();
}

@GetMapping("/OneCourse/{id}")
 public Course GetOneCourse(@PathVariable Long id)
 {
     return courseSerImp.GetCourseById(id);
 }

 @DeleteMapping("/DeleteCourse/{id}")
 public void deleteCourse(@PathVariable Long id) {  courseSerImp.DeleteCourse(id); }

    @PutMapping("/UpdateCourse")
    public Course UpdateCourse (@RequestBody Course course)
    {
        Course c = courseRepo.findById(course.getId()).orElseThrow();

        c.setName(course.getName());
        c.setDescription(course.getDescription());
        c.setPublished(LocalDateTime.now());
        c.setLevel(course.getLevel());

        return courseRepo.save(c);

    }



}
