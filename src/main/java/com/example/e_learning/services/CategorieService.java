package com.example.e_learning.services;

import com.example.e_learning.DTO.Request.CategorieDto;
import com.example.e_learning.DTO.Request.RoleDto;
import com.example.e_learning.DTO.Response.CategorieResponseDto;
import com.example.e_learning.DTO.Response.RoleResponseDto;

import java.util.List;

public interface CategorieService {

    CategorieResponseDto AddCategorie(CategorieDto categorieDto);
    CategorieResponseDto UpdateCategorie(CategorieDto updatedCategorieDto);
    void DeleteCategorie(Long id);
    List<CategorieResponseDto> GetAllCategories();
     CategorieResponseDto GetCatById(Long id)   ;

}
