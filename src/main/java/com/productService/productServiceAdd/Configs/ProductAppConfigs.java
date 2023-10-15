package com.productService.productServiceAdd.Configs;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ProductAppConfigs {

    @Value("${shopsservice.base.url}")
    private String adressBaseURL;

    @Bean
    public ModelMapper modelMapper(){
        return  new ModelMapper();
    }


@LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();

    }
@Bean
public WebClient webClient(){
        return WebClient
                .builder()
                .baseUrl(adressBaseURL)
                .build();
}

}


