package com.xco.spactshop;

import com.xco.spactshop.DataSeeder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@DataMongoTest(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = DataSeeder.class))
class SpactshopApplicationTests {

    @Test
    void contextLoads() {
    }

}
