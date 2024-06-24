package org.example.contacts;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    private List<Contact> contacts = new ArrayList<>();

    @Value("${contacts.file.path}")
    private String contactsFilePath;

    @Override
    public List<Contact> getAllContacts() {
        return contacts;
    }

    @Override
    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    @Override
    public void deleteContactByEmail(String email) {
        contacts.removeIf(contact -> contact.getEmail().equals(email));
    }

    @Override
    public void saveContactsToFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(contactsFilePath))) {
            for (Contact contact : contacts) {
                writer.write(contact.getFullName() + ";" + contact.getPhoneNumber() + ";" + contact.getEmail());
                writer.newLine();
            }
        }
    }
}

