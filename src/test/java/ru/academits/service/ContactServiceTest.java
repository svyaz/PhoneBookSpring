package ru.academits.service;

import org.junit.Ignore;
import org.junit.Test;
import ru.academits.dao.ContactDao;
import ru.academits.model.Contact;
import ru.academits.model.ContactValidation;
import ru.academits.model.ContactsDeletion;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ContactServiceTest {
    private ContactService contactService = new ContactService(new ContactDao());

    @Test
    public void getAllContacts() {
        List<Contact> contactList = contactService.getAllContacts();
        assertEquals(contactList.size(), 2);
        assertEquals(contactList.get(0).getPhone(), "9123456789");
        assertEquals(contactList.get(1).getPhone(), "9131234567");
    }

    @Test
    public void getFilteredContacts() {
        List<Contact> contactList = contactService.getFilteredContacts("913");
        assertEquals(contactList.size(), 1);
        assertEquals(contactList.get(0).getPhone(), "9131234567");
    }

    @Test
    public void addContact() {
        Contact contact = new Contact();
        contact.setFirstName("Alain");
        contact.setLastName("Prost");
        contact.setPhone("111-222-333");
        ContactValidation validation = contactService.addContact(contact);
        List<Contact> contactList = contactService.getFilteredContacts("Pro");

        assertTrue(validation.isValid());
        assertEquals(contactList.size(), 1);
        assertEquals(contactList.get(0).getFirstName(), "Alain");
    }

    @Test
    public void deleteContacts() {
        ContactsDeletion deletion = contactService.deleteContacts(Arrays.asList(2, 1, 4, 10, 1234567890));
        assertEquals(deletion.getDeleteNumber(), 2);
    }
}