package com.example.e_learning.controllers;

import com.example.e_learning.DTO.Request.CourseDto;
import com.example.e_learning.DTO.Request.CourseWithFileDto;
import com.example.e_learning.DTO.Response.CourseResponseDto;
import com.example.e_learning.domain.Course;
import com.example.e_learning.services.Imp.CourseSerImp;
import com.example.e_learning.services.StorageService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/Course")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseController {

    @Autowired
    private CourseSerImp courseSerImp;
    @Autowired
    private StorageService storageService;

    @PostMapping("/save")
    public CourseResponseDto addCourse(@ModelAttribute CourseWithFileDto courseWithFileDto) {

        return courseSerImp.AddCourse(courseWithFileDto);
    }



    @GetMapping("/AllCourses")
    public List<CourseResponseDto> getAllCourses() {
        return courseSerImp.getAllCourses();
    }

    @GetMapping("/OneCourse/{id}")
    public CourseResponseDto GetOneCourse(@PathVariable Long id) {
        return courseSerImp.GetCourseById(id);
    }

    // Serve the file for download
    @GetMapping("/down/{courseId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long courseId) {
        Course course = courseSerImp.getFileCourseById(courseId);
        if (course == null || course.getFile() == null) {
            return ResponseEntity.notFound().build();
        }

        Resource file = storageService.getFileAsResource(course.getFile());
        if (file != null) {
            MediaType mediaType = switch (course.getFile().getExtension().toLowerCase()) {
                case "jpg", "jpeg" -> MediaType.IMAGE_JPEG;
                case "png" -> MediaType.IMAGE_PNG;
                case "gif" -> MediaType.IMAGE_GIF;
                case "pdf" -> MediaType.APPLICATION_PDF;
                default -> MediaType.APPLICATION_OCTET_STREAM;
            };
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + course.getFile().getName() + "\"")
                    .contentType(mediaType)
                    .body(file);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/view/{courseId}")
    public ResponseEntity<Resource> viewFile(@PathVariable Long courseId) {
        Course course = courseSerImp.getFileCourseById(courseId);
        if (course == null || course.getFile() == null) {
            return ResponseEntity.notFound().build();
        }

        Resource file = storageService.getFileAsResource(course.getFile());
        if (file != null) {
            MediaType mediaType = switch (course.getFile().getExtension().toLowerCase()) {
                case "jpg", "jpeg" -> MediaType.IMAGE_JPEG;
                case "png" -> MediaType.IMAGE_PNG;
                case "gif" -> MediaType.IMAGE_GIF;
                case "pdf" -> MediaType.APPLICATION_PDF;
                default -> MediaType.APPLICATION_OCTET_STREAM;
            };
            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .body(file);
        }

        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/DeleteCourse/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseSerImp.DeleteCourse(id);
    }

    @PutMapping("/edit/{id}")
    public CourseResponseDto updateCourse(@PathVariable("id") Long id, @ModelAttribute CourseWithFileDto courseWithFileDto) {
        return courseSerImp.UpdateCourse(courseWithFileDto);
    }
}
