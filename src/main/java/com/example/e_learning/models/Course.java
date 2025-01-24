package com.example.e_learning.models;

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


    @Enumerated(EnumType.STRING)
    public Courselevel level;

    public enum Courselevel {
        BEGINNER, INTERMEDIATE, ADVANCED
    }

}



  /*  public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getPublished() {
        return published;
    }

    public void setPublished(LocalDateTime published) {
        this.published = published;
    }

    public Courselevel getLevel() {
        return level;
    }

    public void setLevel(Courselevel level) {
        this.level = level;
    }



    public Course() {
    }

    public Course(Long id, String name, String description, String category, LocalDateTime published, Courselevel level) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.published = published;
        this.level = level;
    }
*/

