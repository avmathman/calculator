package com.globalsavings.calculator.annotations;

import com.globalsavings.calculator.CalculatorApplication;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
//@WebAppConfiguration
@SpringBootTest(classes = {CalculatorApplication.class})
@Inherited
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public @interface CalculatorIntegrationTest {
}