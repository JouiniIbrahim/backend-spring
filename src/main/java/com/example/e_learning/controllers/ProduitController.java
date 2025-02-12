package com.example.e_learning.controllers;



import com.example.e_learning.DTO.Request.ProduitDto;

import com.example.e_learning.DTO.Response.ProdRespDto;
import com.example.e_learning.DTO.Response.ProduitResponseDto;
import com.example.e_learning.domain.Produit;
import com.example.e_learning.repositories.ProduitRepo;
import com.example.e_learning.services.Imp.CalculSerImp;
import com.example.e_learning.services.Imp.ProduitSerImp;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/produit")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProduitController {

    @Autowired
    private ProduitSerImp produitSerImp;
    @Autowired
    private ProduitRepo produitRepo;
    @Autowired
    private CalculSerImp calculSerImp;

    @PostMapping("/save")
    public ProduitResponseDto AddProduit(@Valid @RequestBody ProduitDto produitDto) {
        Double TTC=calculSerImp.montantTTC(produitDto.getTva(), produitDto.getPrix());
        log.info("TTC: "+TTC);

        produitDto.setPrixTTC(TTC);
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
        Double TTC=  calculSerImp.montantTTC(produitDto.getTva(), produitDto.getPrix());
        produitDto.setPrixTTC(TTC);
        ProduitResponseDto updatedProduit = produitSerImp.UpdateProduit(produitDto);
        return ResponseEntity.ok(updatedProduit);
    }

    @GetMapping("/prix")
    public List<ProdRespDto> highPrixProduits() {

        List<ProdRespDto> result = produitRepo.highPrice();
        log.info("result {} , {}", result, result.size());
        return result;
    }




}
