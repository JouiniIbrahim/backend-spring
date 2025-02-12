package com.example.e_learning.services.Imp;

import com.example.e_learning.services.CalculService;
import org.springframework.stereotype.Service;

@Service
public class CalculSerImp implements CalculService {



    @Override
    public double montantTTC(double tauxTVA, double HT) {

     double montantTVA = ((HT * tauxTVA)/100);
     double montantTTC = montantTVA+HT;
     return montantTTC;

    }



}
