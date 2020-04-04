package jaanin.projekti.BackendinLopputyo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
	public CommandLineRunner bookstoreDemo(LainaRepository repo, LainatyyppiRepository repo2, UserRepository urepository) {
		return (args) -> {
			
			repo2.save(new Lainatyyppi("Erotiikka"));
			repo2.save(new Lainatyyppi("Politiikka"));
			repo2.save(new Lainatyyppi("Eroottinen politiikka"));
			//log.info("Toimii ennen addaamista.");
			repo.save(new Laina("Velallinen 1", 100,repo2.findByName("Erotiikka").get(0)));
			repo.save(new Laina("Velallinen 2",200,repo2.findByName("Politiikka").get(0)));
			repo.save(new Laina("Velallinen 3", 300, repo2.findByName("Eroottinen politiikka").get(0)));
			//TODO Thymeleafiin fixaukset
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", "email@email.com");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN", "posti@posti.com");
			urepository.save(user1);
			urepository.save(user2);

			for (Laina book : repo.findAll()) {
				log.info(book.toString());
			}

			for (Lainatyyppi category : repo2.findAll()) {
				log.info(category.toString());
			}
			
			for (User user: urepository.findAll()) {
				log.info(user.toString());
			}

		};
	}

}
