package org.spring.ensapay.service;

import net.bytebuddy.utility.RandomString;
import org.spring.ensapay.dto.AgentDto;
import org.spring.ensapay.entity.Agent;
import org.spring.ensapay.entity.Backoffice;
import org.spring.ensapay.entity.Client;
import org.spring.ensapay.entity.User;
import org.spring.ensapay.error.Error;
import org.spring.ensapay.repository.AgentRepository;
import org.spring.ensapay.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class AgentService {

    private final Path root = Paths.get("src\\main\\resources\\identities\\agents");

    private JavaMailSender mailSender;
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AgentRepository agentRepository;

    public void registerNewUserAgent(AgentDto newAgent)
            throws Exception {

        String generatedPassword = RandomString.make(8);
        if(this.agentRepository.findAgentByIdentifiant(newAgent.getUsername())!=null){

            throw new Exception("username already exits");
        }
        else {

            String encodedPassword = passwordEncoder.encode(generatedPassword);

            Agent agent = new Agent();
            agent.setAgentFullName(newAgent.getAgentFirstName()+" "+newAgent.getAgentLastName());

            agent.setAgentCIN(newAgent.getAgentCIN());
            agent.setAgentAddress(newAgent.getAgentAddress());
            agent.setAgentEmail(newAgent.getAgentEmail());
            agent.setAgentPhone(newAgent.getAgentPhone());
            agent.setAgentCity(newAgent.getAgentCity());
            agent.setAgentZip(newAgent.getAgentZip());
            agent.setAgentCountry(newAgent.getAgentCountry());
            agent.setIdbackOffice(newAgent.getIdBackOffice());
            agent.setFirstConnection(true);
            agent.setCreationDate(LocalDateTime.now());
            User agentUser = new User();
            agentUser.setRoleName("Agent");
            agentUser.setUsername(newAgent.getUsername());
            agentUser.setUserPassword(passwordEncoder.encode(encodedPassword));
            agent.setAgentUser(agentUser);

            agentRepository.save(agent);
            sendAgentEmail(newAgent, generatedPassword);


        }
    }


    public void sendAgentEmail(AgentDto agent, String otpPassword)
            throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("ensapay_2022@outlook.com");
        helper.setTo(agent.getAgentEmail());

        String subject = "Here's your Credentials!";
        String content = "<p>Hello Agent " + agent.getAgentFirstName() + " " + agent.getAgentLastName() + "</p>"
                + "<p>For security reason, you're required to use the following credentials "

                + "One Time Password to login:</p>"
                + "<p><b>" + otpPassword + "</b></p>"
                + "<br>"
                + "<p>Note: this credentials will let you login to our Agent platform.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }


    public void initAgent() {

        Agent agent = new Agent();
        agent.setAgentFullName("yessine jaoua");

        agent.setAgentAddress("Tunis");
        agent.setAgentPhone("O671");
        agent.setAgentCIN("BG7865");
        agent.setAgentEmail("zessabri80@gmail.com");
        agent.setFirstConnection(true);
        agent.setAgentZip("20520");
        agent.setAgentCountry("Morocco");
        agent.setAgentCity("Casablanca");
        User agentUser = new User();
        agentUser.setUsername("agent1");
        agentUser.setUserPassword(passwordEncoder.encode("12345678"));
        agentUser.setRoleName("Agent");
        agent.setAgentUser(agentUser);

        agentRepository.save(agent);

    }


    public void save(MultipartFile file) {
        try {
            Timestamp timestamps =  new Timestamp(new Date().getTime());
            String unique_number =String.valueOf(timestamps.getTime());
            String fileName = unique_number+"_"+file.getOriginalFilename();
            Files.copy(file.getInputStream(), this.root.resolve(fileName));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the identities. Error: " + e.getMessage());
        }
    }

    public Agent getAgentProfile(String username) {
        return this.agentRepository.findAgentByIdentifiant(username);
    }


    public List<Client> getClients(Long id) {
        return  this.clientRepository.findByidAgent(id);
    }
    public List<Client> getClients(Long id,String name) {
        return  this.clientRepository.findByidAgentAndName(id,name);
    }
}
