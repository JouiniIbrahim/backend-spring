package com.example.e_learning.controllers;


import com.example.e_learning.DTO.Response.ProduitResponseDto;
import com.example.e_learning.domain.Produit;
import com.example.e_learning.services.Imp.CalculSerImp;
import com.example.e_learning.services.Imp.ProduitSerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calcul")
public class CalculController {

    @Autowired
    private CalculSerImp calculSerImp;
    @Autowired
    private ProduitSerImp produitSerImp;

    @GetMapping("/ttc/{id}")
    public Double TTC(@PathVariable Long id) {
        ProduitResponseDto produit = produitSerImp.GetProduitById(id);
        Long Ht = produit.getPrix();
        Long tauxTva = produit.getTva();
        return calculSerImp.montantTTC(tauxTva, Ht);


    }




}
