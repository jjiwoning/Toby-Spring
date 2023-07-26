package com.example.tobyspring.user.dao.template.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class CalculatorTest {

    @Test
    void test1() throws IOException {
        Calculator calculator = new Calculator();
        System.out.println(getClass().getResource("/numbers.txt"));
        Integer result = calculator.calSum(getClass().getResource("numbers.txt").getPath());
        Assertions.assertThat(result).isEqualTo(10);
    }
}
