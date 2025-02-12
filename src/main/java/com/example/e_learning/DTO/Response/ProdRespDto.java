package com.example.e_learning.DTO.Response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
public class ProdRespDto {

    private Long id;

    private Double prixTTC=null;

    public ProdRespDto(Double prixTTC, Long id) {
        this.prixTTC = prixTTC;
        this.id = id;
    }
}
