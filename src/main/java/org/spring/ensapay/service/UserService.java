package org.spring.ensapay.service;

import net.bytebuddy.utility.RandomString;
import org.spring.ensapay.entity.Role;
import org.spring.ensapay.entity.User;
import org.spring.ensapay.repository.RoleRepository;
import org.spring.ensapay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    PasswordEncoder passwordEncoder;

    public String registerNewUserClient(User user) throws MessagingException,
            UnsupportedEncodingException {

        String generatedPassword = RandomString.make(8);

        String encodedPassword = passwordEncoder.encode(generatedPassword);

        Role role = roleRepository.findById("Client").get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        User client = new User();
        client.setUserFirstName(user.getUserFirstName());
        client.setUserLastName(user.getUserLastName());
        client.setUserPhone(user.getUserPhone());
        client.setUserCIN(user.getUserCIN());
        client.setUserName(user.getUserName());
        client.setClientProduct(user.getClientProduct());
        client.setSolde(user.getSolde());
        client.setUserPassword(encodedPassword);
        client.setRole(roles);
/*
        Set<User> users = new HashSet<>();
        users.add(user);
        role.setUsers(users);
*/
        userRepository.save(client);

        sendEmail(user,generatedPassword);

        return "Client succesffuly added" ;
    }



    public String registerNewUserAgent(User user) throws MessagingException,
            UnsupportedEncodingException {

        String generatedPassword = RandomString.make(8);

        String encodedPassword = passwordEncoder.encode(generatedPassword);

        Role role = roleRepository.findById("Agent").get();

        Set<Role>  roles = new HashSet<>();
        roles.add(role);


        User agent = new User();
        agent.setUserFirstName(user.getUserFirstName());
        agent.setUserLastName(user.getUserLastName());
        agent.setUserPhone(user.getUserPhone());
        agent.setUserCIN(user.getUserCIN());
        agent.setUserName(user.getUserName());
        agent.setUserPassword(encodedPassword);
        agent.setRole(roles);
/*
        Set<User> users = new HashSet<>();
        users.add(user);
        role.setUsers(users);

 */

        userRepository.save(agent);

        sendEmail(user,generatedPassword);

        return "Agent succesffuly added" ;
    }

/*
    public String resetPassword(String email) {

        return email;
    }

 */

    public void sendEmail(User user, String otpPassword) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("zakariaessabri@outlook.com");
        helper.setTo(user.getUserName());

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

    public void initRoleAndUser() {

        Role agentRole = new Role();
        agentRole.setRoleName("Agent");
        roleRepository.save(agentRole);

        //Adding Client to Role table
        Role clientRole = new Role();
        clientRole.setRoleName("Client");
        roleRepository.save(clientRole);

        //Adding BackOffice to Role table and backOfficeUser to User table

        User backOfficeUser = new User();
        backOfficeUser.setUserFirstName("BackOfficeFIRSTNAME");
        backOfficeUser.setUserLastName("BackOfficeLASTNAME");
        backOfficeUser.setUserPhone("0671886710");
        backOfficeUser.setUserCIN("BJ908070");
        backOfficeUser.setUserName("zaka.raja.1949@gmail.com");
        backOfficeUser.setUserPassword(passwordEncoder.encode("backOffice123"));

        Role roleBackOffice = new Role();
        roleBackOffice.setRoleName("BackOffice");
        Set<Role> roleBack = new HashSet<>();
        roleBack.add(roleBackOffice);
        backOfficeUser.setRole(roleBack);
/*
        Set<User> userBack = new HashSet<>();
        userBack.add(backOfficeUser);
        roleBackOffice.setUsers(userBack);*/

        userRepository.save(backOfficeUser);

    }


}
