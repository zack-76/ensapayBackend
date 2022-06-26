package org.spring.ensapay.webservice;

import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;
import net.bytebuddy.utility.RandomString;
import org.spring.ensapay.dto.ValidatePaymentDto;
import org.spring.ensapay.entity.Facture;
import org.spring.ensapay.entity.ValidatePayment;
import org.spring.ensapay.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private ClientRepository clientRepository;


    @Autowired
    private FactureRepository factureRepository;


    public String sendValidatetoken(String username) {
        ValidatePayment validatePayment = validatePaymentRepository.findById(username).get();
        return validatePayment.getToken();
    }

    public Map<String, String> getImpay(String reference, String username) {

        ValidatePayment validatePayment = new ValidatePayment();
        validatePayment.setUsername(username);
        validatePayment.setToken(RandomString.make(6));
        validatePaymentRepository.save(validatePayment);

        Map<String, Integer> impays = new HashMap<>();
        impays.put("12ABT5670K", 300);
        impays.put("4356LH8908", 1000);
        impays.put("5686CHILKO", 200);
        impays.put("0671886710", 250);
        impays.put("0758091080", 100);
        Map<String, String> ImpayQrCode = new HashMap<>();


        int Impay = impays.entrySet().stream().filter(i -> reference.equals(i.getKey()))
                .map(Map.Entry::getValue).findFirst().orElse(null);
        ImpayQrCode.put("donnateur", this.clientRepository.findClientFullNameByClientUserUsername(username));
        ImpayQrCode.put("impay", Impay + "");
        ImpayQrCode.put("QrCode", RandomString.make(4));
        return ImpayQrCode;
    }

    public String validate(ValidatePaymentDto validatePaymentDto, String username) {

        Integer clientSolde = clientRepository.findClientSoldeByClientId(username);
        if (clientSolde >= validatePaymentDto.getImpaye()) {
            clientRepository.updateClientSoldeByClientId(clientSolde -= validatePaymentDto.getImpaye(), username);
            addFacture(username, validatePaymentDto.getNameCreditor(),
                    validatePaymentDto.getNameDept(),
                    validatePaymentDto.getImpaye());
            return "success";
        } else {
            throw new RuntimeException("error");
        }

    }


    public void addFacture(String username, String nameCreditor, String nameDebt, Integer impayé) {
        Facture facture = new Facture();
        facture.setReference(new Random().nextInt(999998 + 1) + 100000);
        String clientFullName = clientRepository.findClientFullNameByClientUserUsername(username);
        facture.setNumeroClient(username);
        facture.setClientName(clientFullName);
        facture.setCreditorName(nameCreditor);
        facture.setDebtName(nameDebt);
        facture.setImpaye(impayé);
        factureRepository.save(facture);
    }

    public void sendValidateSms(String username) {

        ValidatePayment validatePayment = validatePaymentRepository.findById(username).get();

        String message = "Hello Client: \n"
                + "Please enter this Token to verify your identity: "
                + validatePayment.getToken();

        TextMessage msg = new TextMessage("Ensa Pay Service",
                "+212" + username.substring(1),
                message
        );

        VonageClient client =
                VonageClient.builder().apiKey("cb282c90").apiSecret("zVM3NTagQbOITp83").build();

        SmsSubmissionResponse response = client.getSmsClient().submitMessage(msg);

        if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
            System.out.println("Message sent successfully.");
        } else {
            System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
        }

    }


    public List<Facture> getFactures() {
        return factureRepository.findAll();
    }


    public List<Facture> getFacutreByClientName(String username) {

        return factureRepository.findByNumeroClient(username);
    }

    public List<Facture> getFacutreByClientNameCreditor(String username, String creditor) {

        return factureRepository.findByClientNameAndCreditorName(username, creditor);
    }

    public void validateToken(String username, String token) throws Exception {
        ValidatePayment validatePayment = validatePaymentRepository.findByUsername(username);

        if (token.equals(validatePayment.getToken())) {
            validatePaymentRepository.deleteById(username);

        } else {
            throw new Exception();
        }

    }
}