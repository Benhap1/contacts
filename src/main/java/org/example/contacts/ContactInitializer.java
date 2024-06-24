package org.example.contacts;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Configuration
@Profile("init")
public class ContactInitializer {
    @Bean
    CommandLineRunner initContacts(ContactService contactService, @Value("${contacts.init.file.path}") String initFilePath) {
        return args -> {
            try (BufferedReader reader = new BufferedReader(new FileReader(initFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts.length == 3) {
                        Contact contact = new Contact(parts[0], parts[1], parts[2]);
                        contactService.addContact(contact);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
}
