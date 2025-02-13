package com.example.e_learning.repositories;


import com.example.e_learning.domain.FileAttachement;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FileAttachementRepo extends JpaRepository<FileAttachement, Long> {

}
