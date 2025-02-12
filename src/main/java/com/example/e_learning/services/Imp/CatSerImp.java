package com.example.e_learning.services.Imp;


import com.example.e_learning.DTO.Request.CategorieDto;
import com.example.e_learning.DTO.Response.CategorieResponseDto;
import com.example.e_learning.Mapper.CategorieMapper;
import com.example.e_learning.domain.Categorie;
import com.example.e_learning.repositories.CategorieRepo;
import com.example.e_learning.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.e_learning.Mapper.CategorieMapper.ToDto;
import static com.example.e_learning.Mapper.CategorieMapper.ToEntity;


@Service
public class CatSerImp implements CategorieService {


    @Autowired
    private CategorieRepo categorieRepo;


    @Override
    public CategorieResponseDto AddCategorie(CategorieDto categorieDto) {


        boolean categorieExists = GetAllCategories().stream()
                .anyMatch(cat -> (cat.getNom()).equals(categorieDto.getNom()));

        if (!categorieExists) {

            Categorie categorie = ToEntity(categorieDto);

            Categorie savedCat = categorieRepo.save(categorie);
            return ToDto(savedCat);
        }
        return null;
    }

    @Override
    public List<CategorieResponseDto> GetAllCategories() {

        return categorieRepo.findAll().stream()
                .map(CategorieMapper::ToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategorieResponseDto UpdateCategorie(CategorieDto categorieDto) {
        Categorie existingCat = categorieRepo.findById(categorieDto.getId())
                .orElseThrow(() -> new RuntimeException("Categorie Not Found"));
        existingCat.setNom(categorieDto.getNom());
        Categorie updatedCat = categorieRepo.save(existingCat);
        return ToDto(updatedCat);

    }

    @Override
    public void DeleteCategorie(Long id) {
        categorieRepo.deleteById(id);
    }

    @Override
    public CategorieResponseDto GetCatById(Long id) {
        Categorie categorie= categorieRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Categorie Not Found with id: " + id));
        return ToDto(categorie);
    }

}


