package org.spring.ensapay.service;

import net.bytebuddy.utility.RandomString;
import org.spring.ensapay.dto.AgentDto;
import org.spring.ensapay.entity.Agent;
import org.spring.ensapay.entity.Backoffice;
import org.spring.ensapay.entity.User;
import org.spring.ensapay.repository.AgentRepository;
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

@Transactional
@Service
public class AgentService {

    private final Path root = Paths.get("src\\main\\resources\\identities\\agents");

    private JavaMailSender mailSender;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AgentRepository agentRepository;

    public String registerNewUserAgent(AgentDto newAgent)
            throws MessagingException, UnsupportedEncodingException {

        String generatedPassword = RandomString.make(8);
        String generatedUsername = RandomString.make(10);
        String encodedPassword = passwordEncoder.encode(generatedPassword);

        Agent agent = new Agent();
        agent.setAgentFirstName(newAgent.getAgentFirstName());
        agent.setAgentLastName(newAgent.getAgentLastName());
        agent.setAgentCIN(newAgent.getAgentCIN());
        agent.setAgentAddress(newAgent.getAgentAddress());
        agent.setAgentEmail(newAgent.getAgentEmail());
        agent.setAgentBirthDate(newAgent.getAgentBirthDate());
        agent.setAgentPhone(newAgent.getAgentPhone());
        agent.setFirstConnection(true);
        User agentUser = new User();
        agentUser.setRoleName("Agent");
        agentUser.setUsername(generatedUsername);
        agentUser.setUserPassword(passwordEncoder.encode(encodedPassword));
        agent.setAgentUser(agentUser);
        agentRepository.save(agent);
        sendAgentEmail(newAgent, generatedUsername, generatedPassword);
        return "Agent successfully added";
    }


    public void sendAgentEmail(AgentDto agent, String generatedUsername, String otpPassword)
            throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("ensapay_2022@outlook.com");
        helper.setTo(agent.getAgentEmail());

        String subject = "Here's your Credentials!";
        String content = "<p>Hello Agent " + agent.getAgentFirstName() + " " + agent.getAgentLastName() + "</p>"
                + "<p>For security reason, you're required to use the following credentials "
                + "Username to login:</p>"
                + "<p><b>" + generatedUsername + "</b></p>"
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
        agent.setAgentFirstName("yessine");
        agent.setAgentLastName("Jaoua");
        agent.setAgentAddress("Tunis");
        agent.setAgentPhone("O671");
        agent.setAgentCIN("BG7865");
        agent.setAgentBirthDate("16-10-1999");
        agent.setAgentEmail("zessabri80@gmail.com");
        agent.setFirstConnection(true);
        User agentUser = new User();
        agentUser.setUsername("agent1");
        agentUser.setUserPassword(passwordEncoder.encode("12345678"));
        agentUser.setRoleName("Agent");
        agent.setAgentUser(agentUser);


        agentRepository.save(agent);

    }


    public void save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the identities. Error: " + e.getMessage());
        }
    }

    public Agent getAgentProfile(String username) {
        return this.agentRepository.findAgentByIdentifiant(username);
    }


}
