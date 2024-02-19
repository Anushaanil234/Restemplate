package Vendingmachine.config;

import Vendingmachine.logginginterceptor.LoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

        @Bean
        public RestTemplate getRestTemplate() {
            RestTemplate restTemplate = new RestTemplate(
                    new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
            List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
            interceptors.add(new LoggingInterceptor());
            restTemplate.setInterceptors(interceptors);

            // Configure the ClientHttpRequestFactory if needed
            restTemplate.setRequestFactory(clientHttpRequestFactory());
            return restTemplate;
        }

        private ClientHttpRequestFactory clientHttpRequestFactory() {
            SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
            factory.setConnectTimeout(5000);
            factory.setReadTimeout(5000);
            return factory;
        }
    }

