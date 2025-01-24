package com.example.e_learning.DTO.Response;


import com.example.e_learning.models.Course;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CourseResponseDto {
    private Long id;
    private String name;
    private String description;
    private String category;
    private LocalDateTime published;
    private Course.Courselevel level;
}
