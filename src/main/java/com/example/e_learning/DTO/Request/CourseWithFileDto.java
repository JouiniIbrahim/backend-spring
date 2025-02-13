package com.example.e_learning.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseWithFileDto {
    private CourseDto courseDto;
    private MultipartFile file;

}
