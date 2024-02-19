package Vendingmachine;


import Vendingmachine.config.LoggingController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GroovyApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroovyApplication.class, args);
		new LoggingController().index();

	}
}
//	@Bean
//	public RestTemplate getRestTemplate() {
//		RestTemplate restTemplate = new RestTemplate(
//		new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
//		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
//		interceptors.add(new LoggingInterceptor());
//		restTemplate.setInterceptors(interceptors);
//
//		try {
//		} catch (ResourceAccessException e) {
//			// Handle ResourceAccessException
//			e.printStackTrace();
//			System.err.println("An error occurred while accessing the resource: " + e.getMessage());
//		} catch (RestClientException e) {
//			// Handle RestClientException
//			e.printStackTrace();
//			System.err.println("An error occurred in the REST client: " + e.getMessage());
//		}
//		// Configure the ClientHttpRequestFactory if needed
//		restTemplate.setRequestFactory(clientHttpRequestFactory());
//		return restTemplate;
//	}
//
//	private ClientHttpRequestFactory clientHttpRequestFactory() {
//		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
//		factory.setConnectTimeout(5000);
//		factory.setReadTimeout(5000);
//		return factory;
//	}
//}