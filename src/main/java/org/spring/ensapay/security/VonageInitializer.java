package org.spring.ensapay.security;

import com.vonage.client.VonageClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class VonageInitializer {

    public VonageInitializer(){
        VonageClient client = VonageClient.builder().
                apiKey("7f7f3557").apiSecret("Ucfjbo05oUzdl9C5").build();
        log.info("Vonage initialized");
    }
}
