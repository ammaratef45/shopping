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

import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
public class ProductApplication implements CommandLineRunner {

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
   //System.out.println(categoryService.save(category).getId());
}}
