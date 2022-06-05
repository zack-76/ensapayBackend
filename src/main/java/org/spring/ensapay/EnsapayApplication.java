package org.spring.ensapay;

import org.spring.ensapay.entity.Backoffice;
import org.spring.ensapay.entity.User;
import org.spring.ensapay.service.BackofficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EnsapayApplication  {

	public static void main(String[] args) {
		SpringApplication.run(EnsapayApplication.class, args);
	}
}
