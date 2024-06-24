package org.example.controller;

import org.example.contacts.Contact;
import org.example.contacts.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleController {
    private final ContactService contactService;

    @Autowired
    public ConsoleController(ContactService contactService) {
        this.contactService = contactService;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите действие: (1) Показать контакты, (2) Добавить контакт, (3) Удалить контакт, (4) Сохранить в файл, (5) Выйти");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    showContacts();
                    break;
                case 2:
                    addContact(scanner);
                    break;
                case 3:
                    deleteContact(scanner);
                    break;
                case 4:
                    saveContacts();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Неверный выбор");
            }
        }
    }

    private void showContacts() {
        List<Contact> contacts = contactService.getAllContacts();
        for (Contact contact : contacts) {
            System.out.println(contact.getFullName() + " | " + contact.getPhoneNumber() + " | " + contact.getEmail());
        }
    }

    private void addContact(Scanner scanner) {
        System.out.println("Введите контакты:  Ф. И. О.; номер телефона; адрес электронной почты");
        String input = scanner.nextLine();
        String[] parts = input.split(";");
        if (parts.length == 3) {
            Contact contact = new Contact(parts[0], parts[1], parts[2]);
            contactService.addContact(contact);
            System.out.println("Контакт добавлен.");
        } else {
            System.out.println("Неверный формат ввода.");
        }
    }

    private void deleteContact(Scanner scanner) {
        System.out.println("Введите адрес электронной почты контакта для удаления:");
        String email = scanner.nextLine();
        contactService.deleteContactByEmail(email);
        System.out.println("Контакт удален.");
    }

    private void saveContacts() {
        try {
            contactService.saveContactsToFile();
            System.out.println("Контакты сохранены.");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении контактов.");
        }
    }
}

