package org.spring.ensapay.service;

import net.bytebuddy.utility.RandomString;
import org.spring.ensapay.dto.ClientDto;
import org.spring.ensapay.entity.Agent;
import org.spring.ensapay.entity.Client;
import org.spring.ensapay.entity.User;
import org.spring.ensapay.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
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
public class ClientService {

    private final Path root = Paths.get("src\\main\\resources\\identities\\clients");


    @Autowired(required = true)
    private JavaMailSender mailSender;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    private ClientRepository clientRepository;

    public String registerNewUserClient(ClientDto newClient)
            throws MessagingException, UnsupportedEncodingException {

        String generatedPassword = RandomString.make(8);
        String encodedPassword = passwordEncoder.encode(generatedPassword);

        Client client = new Client();
        client.setClientFirstName(newClient.getClientFirstName());
        client.setClientLastName(newClient.getClientLastName());
        client.setClientCIN(newClient.getClientCIN());
        client.setClientEmail(newClient.getClientEmail());
        client.setClientSolde(newClient.getClientSolde());
        client.setClientCountry(newClient.getClientCountry());
        client.setClientAddress(newClient.getClientAddress());
        client.setClientCity(newClient.getClientCity());
        client.setClientZip(newClient.getClientZip());
        client.setClientPhone(newClient.getClientPhone());
        client.setFirstConnection(true);
        User clientUser = new User();
        clientUser.setUsername(newClient.getClientPhone());
        clientUser.setRoleName("Client");
        clientUser.setUserPassword(passwordEncoder.encode("12345678"));
        client.setClientUser(clientUser);

        clientRepository.save(client);

        //sendClientEmail(newClient,generatedPassword);

        return "Client successfully added";
    }


    public void sendClientEmail(ClientDto client, String otpPassword)
            throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("ensapay_2022@outlook.com");
        helper.setTo(client.getClientEmail());

        String subject = "Here's your Credentials";


        String content = "<p>Hello Client " + client.getClientFirstName() + " " + client.getClientLastName() + "</p>"
                + "<p>For security reason, you're required to use the following "
                + "Username to login:</p>"
                + "<p><b>" + client.getClientPhone() + "</b></p>"
                + "One Time Password to login:</p>"
                + "<p><b>" + otpPassword + "</b></p>"
                + "<br>"
                + "<p>Note: this Credentials will let you login to our Client platform.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }



    public void save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the identities. Error: " + e.getMessage());
        }
    }


    public Integer getSolde(Long clientId) {
        return clientRepository.findClientSoldeByClientId(clientId);
    }

    public Client getClientProfile(String username) {
        return this.clientRepository.findClientByIdentifiant(username);
    }

}
