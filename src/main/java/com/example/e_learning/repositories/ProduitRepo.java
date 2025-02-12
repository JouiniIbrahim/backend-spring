package com.example.e_learning.repositories;


import com.example.e_learning.DTO.Response.ProdRespDto;
import com.example.e_learning.DTO.Response.ProduitResponseDto;
import com.example.e_learning.domain.Produit;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProduitRepo extends JpaRepository<Produit, Long> {

    @Query(value = "SELECT new com.example.e_learning.DTO.Response.ProdRespDto(p.prixTTC,p.id)  FROM Produit p ORDER BY  p.prixTTC DESC limit  5")
    List<ProdRespDto> highPrice();
}
