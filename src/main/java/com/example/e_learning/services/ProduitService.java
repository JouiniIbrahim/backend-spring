package com.example.e_learning.services;

import com.example.e_learning.DTO.Request.CourseDto;
import com.example.e_learning.DTO.Request.ProduitDto;
import com.example.e_learning.DTO.Response.CourseResponseDto;
import com.example.e_learning.DTO.Response.ProduitResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProduitService {

     ProduitResponseDto AddProduit(ProduitDto produitDto);
    ProduitResponseDto UpdateProduit(ProduitDto updatedProduitDto);
    ResponseEntity<String> DeleteProduit(Long id );
     List<ProduitResponseDto> getAllProduits();
    ProduitResponseDto GetProduitById(Long id);
}
