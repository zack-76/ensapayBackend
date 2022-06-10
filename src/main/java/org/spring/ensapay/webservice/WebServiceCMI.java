package org.spring.ensapay.webservice;

import org.spring.ensapay.dto.ValidatePaymentDto;
import org.spring.ensapay.entity.Creditor;
import org.spring.ensapay.entity.Facture;
import org.spring.ensapay.entity.ValidatePayment;
import org.spring.ensapay.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@Transactional
public class WebServiceCMI {

    @Autowired
    private ValidatePaymentRepository validatePaymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreditorRepository creditorRepository;

    @Autowired
    private DebtRepository debtRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private FactureRepository factureRepository;


    public Integer sendValidatetoken(String username) {
        ValidatePayment validatePayment = validatePaymentRepository.findById(username).get();
        return validatePayment.getToken();
    }

    public Integer getImpay(String reference,String username){

        ValidatePayment validatePayment =  new ValidatePayment();
        validatePayment.setUsername(username);
        validatePayment.setToken(new Random().nextInt(999998 + 1)  + 100000);
        validatePaymentRepository.save(validatePayment);

        Map<String,Integer> impays = new HashMap<>();
        impays.put("12ABT5670K",300);
        impays.put("4356LH8908",1000);
        impays.put("5686CHILKO",200);
        impays.put("0671886710",250);
        impays.put("0758091080",100);

        return impays.entrySet().stream().filter(i -> reference.equals(i.getKey()))
                .map(Map.Entry::getValue).findFirst().orElse(null);
    }

    public String validate( ValidatePaymentDto validatePaymentDto,String username)
            throws MessagingException, UnsupportedEncodingException {

        ValidatePayment validatePayment = validatePaymentRepository.findById(username).get();

        if(validatePaymentDto.getGeneratedToken() == validatePayment.getToken()){
            validatePaymentRepository.deleteById(username);
            Integer clientSolde = clientRepository.findClientSoldeByClientId(validatePaymentDto.getClientId());
            if(clientSolde >= validatePaymentDto.getImpaye()){
//                clientRepository.updateClientSoldeByClientId(clientSolde-=validatePaymentDto.getImpaye(),validatePaymentDto.getClientId());
                addFacture(validatePaymentDto.getClientId(),
                        validatePaymentDto.getCodeCreditor(),
                        validatePaymentDto.getCodeDept(),validatePaymentDto.getImpaye());
                sendValidateEmail(validatePaymentDto.getClientId());
                return "success";
            }else
                return "can't pursuite your operation your solde is lower the facture's debt";
        }
        return "Something went wrong!";
    }


    public void addFacture(Long clientId,String codeCreditor,String codeDept,Integer impayé){
        Facture facture =  new Facture();
        facture.setReference( new Random().nextInt(999998+1)+100000);
        String clientFullName = clientRepository.findClientFirstNameByClientId(clientId)+" "+clientRepository.findClientLastNameByClientId(clientId);
        facture.setClientName(clientFullName);
        String nameCreditor = creditorRepository.findCreditorNameByCodeCreditor(codeCreditor);
        facture.setCreditorName(nameCreditor);
        String nameDebt = debtRepository.findDebtNameByCodeDebt(codeDept);
        facture.setDebtName(nameDebt);
        facture.setImpaye(impayé);
        factureRepository.save(facture);
    }


    public List<Facture> getFactures(){
        return factureRepository.findAll();
    }


    public void sendValidateEmail(Long id)
            throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);

        helper.setFrom("ensapay_2022@outlook.com");
        String clientEmail =  clientRepository.findClientEmailByClientId(id);
        String clientFirstName = clientRepository.findClientFirstNameByClientId(id);
        String clientLastName =  clientRepository.findClientLastNameByClientId(id);
        helper.setTo(clientEmail);

        String subject = "Payment success";
        String content = "<p>Hello Client " + clientFirstName + " "+clientLastName+"</p>"
                + "<p>Your Payment has been with succes"
                + "<p>Note: Our EnsaPay platform give you the best and the secure services.</p>";

        helper.setSubject(subject);

        helper.setText(content,true );

        mailSender.send(message);
    }

    public List<Facture> getFacutreByClientName(String clientName){
        return factureRepository.findByClientName(clientName);
    }
}
