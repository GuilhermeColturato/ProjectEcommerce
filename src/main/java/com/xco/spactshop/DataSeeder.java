package com.xco.spactshop;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xco.spactshop.model.Product;
import com.xco.spactshop.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final ProductService productService;

    public DataSeeder(ProductService productService) {
        this.productService = productService;
    }

    @Value("${data.seeder.enabled:true}")
    private boolean seederEnabled;

    @Override
    public void run(String... args) throws Exception {
        if (seederEnabled && productService.findAll("", "", org.springframework.data.domain.Pageable.unpaged()).getTotalElements() == 0) {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Product>> typeReference = new TypeReference<List<Product>>() {};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/data/products.json");
            List<Product> products = mapper.readValue(inputStream, typeReference);
            products.forEach(productService::save);
            System.out.println("Products seeded");
        }
    }
}
