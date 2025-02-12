package com.example.e_learning.DTO.Response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProduitResponseDto {

    private Long id;
    private String nom;
    private String description;
    private Long prix;
    private Long tva;
    private Double prixTTC;
//    private Long categorieId;
    private CategorieResponseDto categorie;
}
