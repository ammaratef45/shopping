package edu.miu.groupx.product.productservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
=======
import org.springframework.context.annotation.Bean;

>>>>>>> b7256c84e7c5252921a21e7df85793946420a9af

@SpringBootApplication
@EnableEurekaClient
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
