package edu.miu.groupx.product.productservice;

import edu.miu.groupx.product.productservice.models.Category;
import edu.miu.groupx.product.productservice.models.Product;
import edu.miu.groupx.product.productservice.models.ProductImages;
import edu.miu.groupx.product.productservice.models.ProductWarehouse;
import edu.miu.groupx.product.productservice.service.CategoryService;
import edu.miu.groupx.product.productservice.service.ProductService;
import edu.miu.groupx.product.productservice.utils.S3Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

@EnableSwagger2
@SpringBootApplication
public class ProductApplication implements CommandLineRunner {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("edu.miu.groupx.product.productservice"))
                .build()
                .apiInfo(metaData());
   }
    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Product Module")
                .description("\"Spring Boot REST API for Online Store\"")
                .version("1.1.0")
                .contact(new Contact("Oussama Jablaoui & Emmanuel Tumwizere", "https://springframework.guru/about/", "develop@teamx.com"))
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Override
    public void run(String... args) throws Exception {


    Category category = new Category();
    category.setName("vest");
}}
