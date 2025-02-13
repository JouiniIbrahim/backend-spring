package com.example.e_learning.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String category;
    private LocalDateTime  published;

    @OneToOne
    @JoinColumn(name = "file_id")  // Foreign key reference to the File entity
    private FileAttachement file;


    @Enumerated(EnumType.STRING)
    public Courselevel level;

    public enum Courselevel {
        BEGINNER, INTERMEDIATE, ADVANCED
    }

}




