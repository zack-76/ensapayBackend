package org.spring.ensapay.webservice;

import org.spring.ensapay.entity.Creditor;
import org.spring.ensapay.repository.CreditorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WebServiceCMI {

    @Autowired
    private CreditorRepository creditorRepository;

    public List getAllCreditor(){

        List<Creditor> creditors =  creditorRepository.findAll();

        return creditors;
    }

    public Integer getImpay(String reference){

        Map<String,Integer> impays = new HashMap<>();
        impays.put("12ABT5670K",300);
        impays.put("4356LH8908",1000);
        impays.put("5686CHILKO",200);
        impays.put("0671886710",250);
        impays.put("0758091080",100);

        return impays.entrySet().stream().filter(i -> reference.equals(i.getKey()))
                .map(Map.Entry::getValue).findFirst().orElse(null);

    }
}
