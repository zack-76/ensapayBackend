package org.spring.ensapay.service;

import org.spring.ensapay.entity.Agent;
import org.spring.ensapay.entity.Backoffice;
import org.spring.ensapay.entity.User;
import org.spring.ensapay.repository.AgentRepository;
import org.spring.ensapay.repository.BackofficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class BackofficeService {

    @Autowired
    private AgentRepository agentRepository;


    @Autowired
    private BackofficeRepository backofficeRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public void initBackoffice() {

        Backoffice backOffice = new Backoffice();
        backOffice.setBackofficeFirstName("Zakaria");
        backOffice.setBackofficeLastName("Essabri");
        backOffice.setBackofficeAddress("Casablanca");
        backOffice.setBackofficePhone("O671886710");
        backOffice.setBackofficeCIN("BG786543");
        backOffice.setBackofficeBirthDate("16-10-2001");
        backOffice.setBackofficeEmail("zaka.raja.1949@gmail.com");
        User backOfficeUser = new User();
        backOfficeUser.setUsername("backoffice1");
        backOfficeUser.setUserPassword(passwordEncoder.encode("12345678"));

        backOfficeUser.setRoleName("Backoffice");
        backOffice.setBackofficeUser(backOfficeUser);


        backofficeRepository.save(backOffice);

    }

    public Backoffice getBacckOfficeProfile(String username) {
        return this.backofficeRepository.findBackOfficeByIdentifiant(username);
    }

    public List<Agent> getAgents(Long id) {
       return  this.agentRepository.findByIdbackOffice(id);
    }
    public List<Agent> getAgents(Long id,String name) {
        return  this.agentRepository.findByIdbackOfficeAndname(id,name);
    }
}
