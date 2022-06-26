package org.spring.ensapay.security;

import com.vonage.client.VonageClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class VonageInitializer {

    public VonageInitializer(){
        VonageClient client =
                VonageClient.builder().apiKey("cb282c90").apiSecret("zVM3NTagQbOITp83").build();
        log.info("Vonage initialized");
    }
}
