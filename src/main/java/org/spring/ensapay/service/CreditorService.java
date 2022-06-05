package org.spring.ensapay.service;

import org.spring.ensapay.entity.Creditor;
import org.spring.ensapay.entity.Debt;
import org.spring.ensapay.repository.CreditorRepository;
import org.spring.ensapay.repository.DebtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CreditorService {

    @Autowired
    private CreditorRepository creditorRepository;

    @Autowired
    private DebtRepository debtRepository;

    public void initCreditors(){
        //Maroc Telecom
        Creditor creditorMarocTelecom = new Creditor();
        creditorMarocTelecom.setCodeCreditor("1234ABCD");
        creditorMarocTelecom.setCategorieCreditor("Reseaux & Telecoms");
        creditorMarocTelecom.setNameCreditor("Maroc Telecom");
        Debt debt1 = new Debt();
        debt1.setCodeDebt("1");
        debt1.setNameDebt("PRODUIT INTERNET SIM");
        Debt debt2 = new Debt();
        debt2.setCodeDebt("2");
        debt2.setNameDebt("PRODUIT FIXE SIM");
        Debt debt3 = new Debt();
        debt3.setCodeDebt("3");
        debt3.setNameDebt("PRODUIT MOBILE SIM");
        Set<Debt> marocTelecomsDepths= new HashSet<>();
        marocTelecomsDepths.add(debt1);
        marocTelecomsDepths.add(debt2);
        marocTelecomsDepths.add(debt3);
        creditorMarocTelecom.setDebts(marocTelecomsDepths);
        creditorRepository.save(creditorMarocTelecom);

        //Lydec
        Creditor creditorLydec = new Creditor();
        creditorLydec.setCodeCreditor("9876YHOL");
        creditorLydec.setCategorieCreditor("ENERGIE");
        creditorLydec.setNameCreditor("Lydec");
        Debt debt4 = new Debt();
        debt4.setCodeDebt("4");
        debt4.setNameDebt("FACTURE LYDEC");
        Set<Debt> lydecDepths= new HashSet<>();
        lydecDepths.add(debt4);
        creditorLydec.setDebts(lydecDepths);
        creditorRepository.save(creditorLydec);
    }
}
