package org.spring.ensapay.service;

import net.bytebuddy.utility.RandomString;
import org.spring.ensapay.dto.AgentDto;
import org.spring.ensapay.entity.Client;
import org.spring.ensapay.entity.ForgetPassword;
import org.spring.ensapay.entity.User;
import org.spring.ensapay.repository.ClientRepository;
import org.spring.ensapay.repository.ForgetPasswordRepository;
import org.spring.ensapay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Transactional
@Service
public class ForgePasswordService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ForgetPasswordRepository forgetPasswordRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired(required = true)
    private JavaMailSender mailSender;

    private Boolean check = false;


    public void ForgetPassword(String username)
            throws MessagingException, UnsupportedEncodingException {
        String token= RandomString.make(4);
        ForgetPassword forgetPassword=new ForgetPassword(username,token);

        String email=this.clientRepository.findClientByClientUserUsername(username);
        if(email!=null) {
            System.out.println(email);
            sendAForgetPasswordEmail(email, token);
            this.forgetPasswordRepository.save(forgetPassword);
        }
        else { throw new IllegalArgumentException("this username is not available ");}

    }

    public Boolean checkToken(String username , String token){
        ForgetPassword forgetPassword = forgetPasswordRepository.findById(username).get();
        if((forgetPassword.getToken()).equals(token)){
            this.forgetPasswordRepository.deleteById(username);
            return true;
        }
        return false;
    }


    public void sendAForgetPasswordEmail(String email,String token)
            throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);

        helper.setFrom("ensapay_2022@outlook.com");
        helper.setTo(email);

        String subject = "Verify your account";
        String content = "<p>Hello! </p>"
                + "<p>For security reason, you're required to enter the following Token to verify your identity "
                + "Username to login:</p>"
                + "<p><b>" + token + "</b></p>"
                + "<br>"
                + "<p>Note: this token will let you reset your password.</p>";

        helper.setSubject(subject);

        helper.setText(content,true );

        mailSender.send(message);
    }
    public void newPassword(String username,String password) throws Exception {

            if (username == null) {
                throw new RuntimeException("username null");
            }
            else if(password==null){
                throw new RuntimeException("password null");
            }
            User user = this.userRepository.findUserByUsername(username);
            user.setUserPassword(passwordEncoder.encode(password));
            this.userRepository.save(user);

    }
}
