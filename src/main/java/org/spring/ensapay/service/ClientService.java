package org.spring.ensapay.service;

import net.bytebuddy.utility.RandomString;
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

    @Autowired
    private JavaMailSender mailSender = new JavaMailSenderImpl();

    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    private ClientRepository clientRepository;

    public String registerNewUserClient(Client newClient)
            throws MessagingException, UnsupportedEncodingException{

        String generatedPassword = RandomString.make(8);
        String encodedPassword = passwordEncoder.encode(generatedPassword);

        Client client = new Client();
        client.setClientFirstName(newClient.getClientFirstName());
        client.setClientLastName(newClient.getClientLastName());
        client.setClientCIN(newClient.getClientCIN());
        client.setClientBirthDate(newClient.getClientBirthDate());
        client.setClientEmail(newClient.getClientEmail());
        client.setClientSolde(newClient.getClientSolde());
        client.setClientAddress(newClient.getClientAddress());
        User clientUser =  new User();
        clientUser.setRoleName("Client");
        clientUser.setUsername(newClient.getClientPhone());
        clientUser.setUserPassword(encodedPassword);
        client.setClientUser(clientUser);

        clientRepository.save(client);

        sendClientEmail(newClient,generatedPassword);

        return "Client successfully added" ;
    }



    public void sendClientEmail(Client client , String otpPassword )
            throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("ensapay_2022@outlook.com");
        helper.setTo(client.getClientEmail());

        String subject = "Here's your Credentials";


        String content = "<p>Hello Client " + client.getClientFirstName() + " "+client.getClientLastName()+"</p>"
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



    public void initClient() {

        Client client = new Client();
        client.setClientFirstName("yessine");
        client.setClientLastName("Jaoua");
        client.setClientAddress("Tunis");
        client.setClientPhone("O671");
        client.setClientCIN("BG7865");
        client.setClientSolde(500);
        client.setClientBirthDate("16-10-1999");
        client.setClientEmail("zessabri80@gmail.com");
        User clientUser = new User();
        clientUser.setUsername("O671");
        clientUser.setUserPassword(passwordEncoder.encode("1234"));
        clientUser.setRoleName("Client");
        client.setClientUser(clientUser);

        clientRepository.save(client);

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

}
