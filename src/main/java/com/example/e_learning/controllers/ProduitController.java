package com.example.e_learning.controllers;



import com.example.e_learning.DTO.Request.ProduitDto;

import com.example.e_learning.DTO.Request.UserDto;
import com.example.e_learning.DTO.Response.ProduitResponseDto;
import com.example.e_learning.DTO.Response.UserResponseDto;
import com.example.e_learning.models.Produit;
import com.example.e_learning.models.User;
import com.example.e_learning.services.Imp.ProduitSerImp;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.e_learning.Mapper.UserMapper.ToDto;

@RestController
@RequestMapping("/produit")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProduitController {

    @Autowired
    private ProduitSerImp produitSerImp;

    @PostMapping("/save")
    public ProduitResponseDto AddProduit(@Valid @RequestBody ProduitDto produitDto) {
        return produitSerImp.AddProduit(produitDto);
    }
    @GetMapping("/all")
    public List<ProduitResponseDto> getAllProduits() {
        return produitSerImp.getAllProduits();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduit(@PathVariable  Long id) {
        return produitSerImp.DeleteProduit(id);
    }

        @GetMapping("/find-one/{id}")
    public ProduitResponseDto GetOneProduit(@PathVariable Long id)
    {
        return produitSerImp.GetProduitById(id);
    }

    @PutMapping("/update")
    public ResponseEntity<ProduitResponseDto> updateProduit( @RequestBody ProduitDto produitDto) {
        if ( produitDto == null) {
            throw new IllegalArgumentException("Produit or ID must not be null");
        }

        ProduitResponseDto updatedProduit = produitSerImp.UpdateProduit(produitDto);
        return ResponseEntity.ok(updatedProduit);
    }


}
