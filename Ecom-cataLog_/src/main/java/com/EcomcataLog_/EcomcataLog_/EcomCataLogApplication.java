package com.EcomcataLog_.EcomcataLog_;

import com.EcomcataLog_.EcomcataLog_.config.MailConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(MailConfig.class)
public class EcomCataLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomCataLogApplication.class, args);
	}

}