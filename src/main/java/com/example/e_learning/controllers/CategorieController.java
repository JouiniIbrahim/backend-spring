package com.example.e_learning.controllers;


import com.example.e_learning.DTO.Request.CategorieDto;
import com.example.e_learning.DTO.Request.RoleDto;
import com.example.e_learning.DTO.Response.CategorieResponseDto;
import com.example.e_learning.DTO.Response.RoleResponseDto;
import com.example.e_learning.services.Imp.CatSerImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Categorie")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CategorieController {
    @Autowired
    private CatSerImp catSerImp;

    @PostMapping("/AddCat")
    public CategorieResponseDto addRole(@Valid @RequestBody CategorieDto categorieDto) {

        return catSerImp.AddCategorie(categorieDto);
    }

    @GetMapping("/AllCats")
    public List<CategorieResponseDto> getAllRoles() {
        return catSerImp.GetAllCategories();
    }
    @GetMapping("/OneCat")
    public CategorieResponseDto getOneCat(long id) {
        return catSerImp.GetCatById(id);
    }
}
