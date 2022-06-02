package org.spring.ensapay.util;

import org.spring.ensapay.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Component
public class EmailSender {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(User user, String otpPassword) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("ensapay202205@outlook.com");
        helper.setTo(user.getUserEmail());

        String subject = "Here's your One Time Password (One Time Password)";

        String content = "<p>Hello " + user.getUserFirstName() + " "+user.getUserLastName()+"</p>"
                + "<p>For security reason, you're required to use the following "
                + "One Time Password to login:</p>"
                + "<p><b>" + otpPassword + "</b></p>"
                + "<br>"
                + "<p>Note: this One Time Password will let you login to our platform.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }
}
