package com.example.tobyspring.user.dao.template.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
    public Integer calSum(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        int sum = 0;
        String line = null;
        while ((line = br.readLine()) != null) {
            sum += Integer.parseInt(line);
        }

        br.close();
        return sum;
    }
}
