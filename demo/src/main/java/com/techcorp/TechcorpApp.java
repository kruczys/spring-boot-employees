package com.techcorp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import com.techcorp.domain.Person;
import com.techcorp.service.EmployeeService;

import java.util.List;
import java.util.ArrayList;

@SpringBootApplication
@ImportResource("classpath:beans.xml")
public class TechcorpApp {

    @Autowired
    private EmployeeService employeeService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(TechcorpApp.class, args);
        TechcorpApp app = context.getBean(TechcorpApp.class);
        app.run(context);
    }

    public void run(ApplicationContext context) {
        try {
            List<Person> employees = employeeService.loadEmployees();
            List<Person> employeesSorted = new ArrayList<>();
            List<Person> employeesFiltered = new ArrayList<>();

            Person ceo = (Person) context.getBean("ceo");
            Person vp = (Person) context.getBean("vp");
            Person secretary = (Person) context.getBean("secretary");

            employees.add(ceo);
            employees.add(vp);
            employees.add(secretary);

            employeesSorted = employeeService.sortByLastName(employees);
            employeesFiltered = employeeService.filterByCompany(employees, "China");

            System.out.println("-------------------------------------------------------------");
            System.out.println("Pracownicy:");
            for (Person employee : employees) {
                System.out.println(employee.getFirstName() + " " + employee.getLastName() + " - " + employee.getEmail() + " - " + employee.getCompany());
            }

            System.out.println("-------------------------------------------------------------");
            System.out.println("Pracownicy posortowani po nazwisku:");
            for (Person employee : employeesSorted) {
                System.out.println(employee.getFirstName() + " " + employee.getLastName() + " - " + employee.getEmail() + " - " + employee.getCompany());
            }

            System.out.println("-------------------------------------------------------------");
            System.out.println("Pracownicy przefiltrowani po firmie \"China\":");
            for (Person employee : employeesFiltered) {
                System.out.println(employee.getFirstName() + " " + employee.getLastName() + " - " + employee.getEmail() + " - " + employee.getCompany());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}