package com.example.e_learning.DTO.Request;


import com.example.e_learning.models.Categorie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProduitDto {

    private Long id;
    private String nom;
    private String description;
    private Long prix;
    private Long tva;
    private Categorie categorie;
}
