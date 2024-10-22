package com.techcorp.service;

import com.techcorp.domain.Person;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

@Service
public class EmployeeService {
    public List<Person> loadEmployees() throws Exception {
        List<Person> employees = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/MOCK_DATA.csv")));
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            employees.add(new Person(data[0], data[1], data[2], data[5]));
        }
        return employees;
    }

    public List<Person> filterByCompany(List<Person> employees, String company) {
        return employees.stream()
                .filter(e -> e.getCompany().equalsIgnoreCase(company))
                .toList();
    }

    public List<Person> sortByLastName(List<Person> employees) {
        return employees.stream()
                .sorted(Comparator.comparing(Person::getLastName))
                .toList();
    }
}