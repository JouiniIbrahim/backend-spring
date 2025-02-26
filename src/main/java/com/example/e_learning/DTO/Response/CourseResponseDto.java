package com.example.e_learning.DTO.Response;


import com.example.e_learning.domain.Course;
import com.example.e_learning.domain.FileAttachement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponseDto {
    private Long id;
    private String name;
    private String description;
    private String category;
    private LocalDateTime published;
    private Course.Courselevel level;


    private String fileName;
    private String fileExtension;


}
