package com.example.e_learning.Mapper;

import com.example.e_learning.DTO.Request.ProduitDto;
import com.example.e_learning.DTO.Response.CategorieResponseDto;
import com.example.e_learning.DTO.Response.ProduitResponseDto;
import com.example.e_learning.domain.Produit;


public class ProduitMapper {

    public static ProduitResponseDto ToDto(Produit produit) {
        ProduitResponseDto responseDto = new ProduitResponseDto();
        responseDto.setId(produit.getId());
        responseDto.setNom(produit.getNom());
        responseDto.setDescription(produit.getDescription());
        responseDto.setPrix(produit.getPrix());
        responseDto.setTva(produit.getTva());
        responseDto.setPrixTTC(produit.getPrixTTC());
    //    responseDto.setCategorieId(produit.getCategorie().getId());
        CategorieResponseDto categorieDto = new CategorieResponseDto();
        categorieDto.setId(produit.getCategorie().getId());
        categorieDto.setNom(produit.getCategorie().getNom());

        responseDto.setCategorie(categorieDto);

        return responseDto;
    }
    public static Produit ToEntity(ProduitDto produitDto)
    {
        Produit produit = new Produit();
        produit.setId(produitDto.getId());
        produit.setNom(produitDto.getNom());
        produit.setDescription(produitDto.getDescription());
        produit.setPrix(produitDto.getPrix());
        produit.setTva(produitDto.getTva());
        produit.setPrixTTC(produitDto.getPrixTTC());
        produit.setCategorie(produitDto.getCategorie());

        return produit;

    }
}
