package com.productService.productServiceAdd.Configs;


import org.modelmapper.ModelMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ProductAppConfigs {



    @Bean
    public ModelMapper modelMapper(){
        return  new ModelMapper();
    }






}


