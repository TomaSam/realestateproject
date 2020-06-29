package lt.practice.realestate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lt.practice.realestate.models.Building;
import lt.practice.realestate.models.PropertyType;
import lt.practice.realestate.repository.BuildingsRepository;

@SpringBootApplication
public class RealestateApplication {

	private static final Logger log = LoggerFactory.getLogger(RealestateApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(RealestateApplication.class, args);
	}
	
	@Bean
	 public CommandLineRunner demo(BuildingsRepository repository) {
	 return (args) -> {
	    
	      repository.save(new Building("Gedimino g. 9, Vilnius", "UAB Money", 1500.80, 1200500.00, PropertyType.INDUSTRIAL));
	      repository.save(new Building("Lvovo g. 27, Vilnius", "Antanas Antanaitis", 200.80, 300500.00, PropertyType.HOUSE));
	      repository.save(new Building("Giedraiciu g. 45-78, Vilnius", "UAB Money", 90.80, 100500.00, PropertyType.APARTMENT));
	      
	      log.info("Buildings found with findAll():");
	      log.info("-------------------------------");
	      for (Building building : repository.findAll()) {
	        log.info(building.toString());
	      }
	      log.info("");
	 };

	 }
}
