package one.digitalinnovation.gerenciamentodepessoas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@RestController
@RequestMapping("/")
public class DioApplication {
private static final String REDIRECT = "redirect:/swagger-ui/";
	@GetMapping
	public ModelAndView swaggerUi(){return new ModelAndView(REDIRECT);}

	@Bean
	public WebClient webClient(WebClient.Builder builder){
		return builder.baseUrl("https://viacep.com.br")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(DioApplication.class, args);
	}
}