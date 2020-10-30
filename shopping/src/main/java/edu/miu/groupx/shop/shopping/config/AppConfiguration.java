package edu.miu.groupx.shop.shopping.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {
    @LoadBalanced
    RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
