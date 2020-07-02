package lt.practice.realestate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//import lt.practice.realestate.models.Building;
//import lt.practice.realestate.models.PropertyType;
//import lt.practice.realestate.repository.BuildingsRepository;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class RealestateApplication {

	private static final Logger log = LoggerFactory.getLogger(RealestateApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(RealestateApplication.class, args);
		log.info("Real Estate registration application initialize.");
	}
//	
//	@Bean
//	public CommandLineRunner demo(BuildingsRepository repository) {
//		 return (args) -> {	    
//		      repository.save(new Building("Gedimino g. 9, Vilnius", "UAB Money", 1500.80, 1200500.00, PropertyType.INDUSTRIAL));
//		      repository.save(new Building("Lvovo g. 27, Vilnius", "Antanas Antanaitis", 200.80, 300500.00, PropertyType.HOUSE));
//		      repository.save(new Building("Giedraiciu g. 45-78, Vilnius", "UAB Money", 90.80, 100500.00, PropertyType.APARTMENT));
//		      
//		      log.info("Buildings data has been genereted.");
//		 };
//	}
	
	
	@Bean
	public Docket swaggerDocket() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("lt.practice.realestate")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Real Estate registration application").version("0.0.1-SNAPSHOT").build();
	}
	
	
}
