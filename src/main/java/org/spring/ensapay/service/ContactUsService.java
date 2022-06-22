package org.spring.ensapay.service;


import org.spring.ensapay.entity.ConatctUs;
import org.spring.ensapay.repository.ContactUsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service

public class ContactUsService {
    @Autowired
    private ContactUsRepo contactUsRepo;

   public void contacfunction(ConatctUs contactUs) throws  Exception{

           this.contactUsRepo.save(contactUs);


    }
    public List<ConatctUs> Contact_affiche_to_agent(Long id){
       return this.contactUsRepo.findByIdAgent(id);
    }
    public List<ConatctUs> Contact_affiche_to_agent(Long id,String phone){
        return this.contactUsRepo.findByIdAgentAndPhoneeee(id,phone);
    }

}
