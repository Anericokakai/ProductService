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



    @Bean
    public ModelMapper modelMapper(){
        return  new ModelMapper();
    }






}


