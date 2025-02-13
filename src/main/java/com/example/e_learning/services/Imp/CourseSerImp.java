package com.example.e_learning.services.Imp;

import com.example.e_learning.DTO.Request.CourseDto;
import com.example.e_learning.DTO.Request.CourseWithFileDto;
import com.example.e_learning.DTO.Response.CourseResponseDto;
import com.example.e_learning.Mapper.CourseMapper;
import com.example.e_learning.domain.Course;
import com.example.e_learning.domain.FileAttachement;
import com.example.e_learning.repositories.CourseRepo;
import com.example.e_learning.repositories.FileAttachementRepo;
import com.example.e_learning.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.e_learning.Mapper.CourseMapper.*;

@Service
public class CourseSerImp implements CourseService {



    @Autowired
    CourseRepo courseRepo;
    @Autowired
    FileAttachementRepo fileAttachementRepo;

    public Course getFileCourseById(Long id) {
        Optional<Course> file = courseRepo.findById(id);
        return file.orElse(null);  // Return null if the file is not found
    }

    // Helper method to save the file
    private FileAttachement saveFile(MultipartFile file) throws IOException {
        FileAttachement fileC= new FileAttachement();
        fileC.setName(file.getOriginalFilename());
        fileC.setExtension(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.') + 1));
        fileC.setData(file.getBytes());  // Save file content as Blob

        MediaType mediaType = switch (fileC.getExtension().toLowerCase()) {
            case "jpg", "jpeg" ->
                MediaType.IMAGE_JPEG;
            case "png" -> MediaType.IMAGE_PNG;
            case "gif" -> MediaType.IMAGE_GIF;
            case "pdf" -> MediaType.APPLICATION_PDF;
            default -> MediaType.APPLICATION_OCTET_STREAM;
        };


        fileC.setContentType(mediaType.toString());

        return fileAttachementRepo.save(fileC);
    }

    @Override
    public CourseResponseDto AddCourse(@ModelAttribute  CourseWithFileDto courseWithFileDto) {
        CourseDto courseDto = courseWithFileDto.getCourseDto();
        MultipartFile file = courseWithFileDto.getFile();

        Course course = ToEntity(courseDto);
        course.setPublished(LocalDateTime.now());

        if (file != null && !file.isEmpty()) {
            try {
                FileAttachement fileC = saveFile(file);
                course.setFile(fileC);
            } catch (IOException e) {
                throw new RuntimeException("Error saving file", e);
            }
        }

        Course savedCourse = courseRepo.save(course);

        return ToDto(savedCourse);
    }

    @Override
    public CourseResponseDto UpdateCourse( @ModelAttribute CourseWithFileDto courseWithFileDto) {
        CourseDto updateCourseDto = courseWithFileDto.getCourseDto();
        MultipartFile file = courseWithFileDto.getFile();

        Course existingCourse = courseRepo.findById(updateCourseDto.getId())
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + updateCourseDto.getId()));

        updateCourseDto.setPublished(existingCourse.getPublished());

        existingCourse = ToEntity(updateCourseDto);

        if (file != null && !file.isEmpty()) {
            try {
                FileAttachement fileC = saveFile(file);
                existingCourse.setFile(fileC);
            } catch (IOException e) {
                throw new RuntimeException("Error saving file", e);
            }
        }

        courseRepo.save(existingCourse);

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
