package com.example.e_learning.Mapper;

import com.example.e_learning.DTO.Request.CategorieDto;
import com.example.e_learning.DTO.Request.RoleDto;
import com.example.e_learning.DTO.Response.CategorieResponseDto;
import com.example.e_learning.DTO.Response.RoleResponseDto;
import com.example.e_learning.models.Categorie;
import com.example.e_learning.models.Role;

public class CategorieMapper {

    public static CategorieResponseDto ToDto(Categorie categorie) {
        CategorieResponseDto responseDto = new CategorieResponseDto();
        responseDto.setId(categorie.getId());
        responseDto.setNom(categorie.getNom());

        return responseDto;
    }

    public static Categorie ToEntity(CategorieDto categorieDto) {
        Categorie categorie = new Categorie();
        categorie.setId(categorieDto.getId());
        categorie.setNom(categorieDto.getNom());

        return categorie;
    }
}
