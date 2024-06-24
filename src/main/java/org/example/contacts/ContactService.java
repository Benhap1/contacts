package org.example.contacts;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


public interface ContactService {
    List<Contact> getAllContacts();
    void addContact(Contact contact);
    void deleteContactByEmail(String email);
    void saveContactsToFile() throws IOException;
}

