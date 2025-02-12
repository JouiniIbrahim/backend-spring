package com.example.e_learning.services.Imp;


import com.example.e_learning.DTO.Request.ProduitDto;
import com.example.e_learning.DTO.Response.ProduitResponseDto;
import com.example.e_learning.Mapper.ProduitMapper;
import com.example.e_learning.domain.Produit;
import com.example.e_learning.repositories.ProduitRepo;
import com.example.e_learning.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.e_learning.Mapper.ProduitMapper.ToDto;
import static com.example.e_learning.Mapper.ProduitMapper.ToEntity;

@Service
public class ProduitSerImp implements ProduitService {

    @Autowired
    ProduitRepo produitRepo;
    @Autowired
    private CalculSerImp calculSerImp;

    @Override
    public ProduitResponseDto AddProduit(ProduitDto produitDto) {
        Produit produit =ToEntity(produitDto);
        produitRepo.save(produit);
        return ToDto(produit);


    }

    @Override
    public ProduitResponseDto UpdateProduit(ProduitDto produitDto) {
        Produit exsitproduit=produitRepo.findById(produitDto.getId())
                .orElseThrow(() -> new RuntimeException("produit Not Found"));

        exsitproduit = ToEntity(produitDto);
        produitRepo.save(exsitproduit);
        return ToDto(exsitproduit);
    }

    @Override
    public ProduitResponseDto GetProduitById(Long id) {
        Produit produit = produitRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("produit Not Found"));
        return ToDto(produit);
    }

    @Override
    public List<ProduitResponseDto> getAllProduits() {
        return produitRepo.findAll().stream().map(ProduitMapper::ToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<String> DeleteProduit(Long id) {
        if(produitRepo.existsById(id)) {
            produitRepo.deleteById(id);
            return  ResponseEntity.ok("Produit Deleted");
        }else return  ResponseEntity.ok("Produit Not Found");

    }






}
