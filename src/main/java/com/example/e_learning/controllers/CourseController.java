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
    public CourseResponseDto AddCourseWithFile(@ModelAttribute CourseWithFileDto courseWithFileDto) {
        String filename = storageService.store(courseWithFileDto.getSupport());
        log.info("Filename is {}", filename);

        if (courseWithFileDto.getCourseDto() != null) {
            courseWithFileDto.getCourseDto().setSupport(filename);
        }
        return courseSerImp.AddCourse(courseWithFileDto.getCourseDto());
    }

    @PostMapping("/AddCourse")
    public CourseResponseDto AddCourse1(@Valid @RequestBody CourseDto courseDto) {
        return courseSerImp.AddCourse(courseDto);
    }

    @GetMapping("/AllCourses")
    public List<CourseResponseDto> getAllCourses() {
        return courseSerImp.getAllCourses();
    }

    @GetMapping("/OneCourse/{id}")
    public CourseResponseDto GetOneCourse(@PathVariable Long id) {
        return courseSerImp.GetCourseById(id);
    }

        @GetMapping("/{id}")
        public ResponseEntity<byte[]> getFile(@PathVariable Long id) throws IOException {
            Course fileEntity = courseSerImp.getFileCourseById(id);

            if (fileEntity == null) {
                return ResponseEntity.notFound().build();
            }

            String filename = fileEntity.getSupport();
            Resource fileResource = storageService.loadFile(filename);

            if (fileResource.exists() || fileResource.isReadable()) {
                String fileExtension = filename.substring(filename.lastIndexOf(".") + 1);
                MediaType mediaType = switch (fileExtension.toLowerCase()) {
                    case "jpg", "jpeg" -> MediaType.IMAGE_JPEG;
                    case "png" -> MediaType.IMAGE_PNG;
                    case "gif" -> MediaType.IMAGE_GIF;
                    case "pdf" -> MediaType.APPLICATION_PDF;
                    default -> MediaType.APPLICATION_OCTET_STREAM;
                };

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                        .contentType(mediaType)
                        .body(Files.readAllBytes(fileResource.getFile().toPath()));
            } else {
                return ResponseEntity.notFound().build();
            }
        }



    @DeleteMapping("/DeleteCourse/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseSerImp.DeleteCourse(id);
    }

    @PutMapping("/UpdateCourse")
    public CourseResponseDto UpdateCourse(@ModelAttribute CourseWithFileDto courseWithFileDto) {
        System.out.println("----------------------------");
        String filename = storageService.store(courseWithFileDto.getSupport());
        log.info("Filename is {}", filename);
        if (courseWithFileDto.getCourseDto() != null) {
            courseWithFileDto.getCourseDto().setSupport(filename);
        }
        return courseSerImp.UpdateCourse(courseWithFileDto.getCourseDto());
    }
}
