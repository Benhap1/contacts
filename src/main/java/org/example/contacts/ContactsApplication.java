package org.example.contacts;

import org.example.controller.ConsoleController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.example")
public class ContactsApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ContactsApplication.class, args);
        ConsoleController consoleController = context.getBean(ConsoleController.class);
        consoleController.run();
    }
}

