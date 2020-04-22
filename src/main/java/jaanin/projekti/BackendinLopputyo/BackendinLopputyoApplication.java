package jaanin.projekti.BackendinLopputyo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import jaanin.projekti.BackendinLopputyo.domain.Asiakas;
import jaanin.projekti.BackendinLopputyo.domain.AsiakasRepository;
import jaanin.projekti.BackendinLopputyo.domain.Laina;
import jaanin.projekti.BackendinLopputyo.domain.LainaRepository;
import jaanin.projekti.BackendinLopputyo.domain.Lainatyyppi;
import jaanin.projekti.BackendinLopputyo.domain.LainatyyppiRepository;
import jaanin.projekti.BackendinLopputyo.domain.User;
import jaanin.projekti.BackendinLopputyo.domain.UserRepository;

@SpringBootApplication
public class BackendinLopputyoApplication {
	private static final Logger log = LoggerFactory.getLogger(BackendinLopputyoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BackendinLopputyoApplication.class, args);
	}
	

	@Bean // tähän voi tunkea loputtomasti argumentteja näköjään?
	public CommandLineRunner bookstoreDemo(LainaRepository lainat, LainatyyppiRepository lainatyypit, UserRepository userit, AsiakasRepository asiakkaat) {
		return (args) -> {
			
			
			Asiakas asiakas1 = new Asiakas("1234567890","Ville Velkavankeus");
			Asiakas asiakas2 = new Asiakas("9876543210","Keijo Korko");
			Asiakas asiakas3 = new Asiakas("1231231230","Pekka Perintä");

			asiakkaat.save(asiakas1);
			asiakkaat.save(asiakas2);
			asiakkaat.save(asiakas3);
			
			Lainatyyppi lainatyyppi1 = new Lainatyyppi("Kulutusluotto", 5.5, 12);
			Lainatyyppi lainatyyppi2 = new Lainatyyppi("Käyttölaina", 17, 36);
			Lainatyyppi lainatyyppi3 = new Lainatyyppi("Klo 3 Aammuyöstä-luotto", 25, 48);

			lainatyypit.save(lainatyyppi1);
			lainatyypit.save(lainatyyppi2);
			lainatyypit.save(lainatyyppi3);

			
			
			//log.info("Toimii ennen addaamista.");
			lainat.save(new Laina(asiakas1,lainatyyppi1, 1000));
			lainat.save(new Laina(asiakas2,lainatyyppi2, 2000));
			lainat.save(new Laina(asiakas3,lainatyyppi3, 3000));
			//TODO Thymeleafiin fixaukset
			//User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", "email@email.com");
			//User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN", "posti@posti.com");
			User user2 = new User("admin", "$2a$10$g91l/ADAQ0sG9m9l7T4nlOcFqUNtstjrmvzvwOOtNy4snftB1b.J.", "ADMIN", "posti@posti.com"); //turvallinensalasana
			
			//userit.save(user1);
			userit.save(user2);

			for (Laina book : lainat.findAll()) {
				log.info(book.toString());
			}

			for (Lainatyyppi category : lainatyypit.findAll()) {
				log.info(category.toString());
			}
			
			for (User user: userit.findAll()) {
				log.info(user.toString());
			}

		};
	}

}
