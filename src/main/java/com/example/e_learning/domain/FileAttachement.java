package com.example.e_learning.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.MediaType;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FileAttachement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String extension;

    @Lob
    private byte[] data;

    private String contentType;


}



