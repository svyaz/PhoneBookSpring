package ru.academits.phonebook;

import org.junit.Test;
import ru.academits.dao.ContactDao;
import ru.academits.model.Contact;
import ru.academits.model.ContactValidation;
import ru.academits.model.ContactsDeletion;
import ru.academits.service.ContactService;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PhoneBookControllerTest {
    private PhoneBookController phoneBookController = new PhoneBookController(new ContactService(new ContactDao()));

    @Test
    public void getAllContactsTest() {
        List<Contact> contactList = phoneBookController.getAllContacts();
        assertEquals(contactList.size(), 2);
        assertEquals(contactList.get(0).getPhone(), "9123456789");
        assertEquals(contactList.get(1).getPhone(), "9131234567");
    }

    @Test
    public void getFilteredContactsTest() {
        List<Contact> contactList = phoneBookController.getFilteredContacts("89");
        assertEquals(contactList.size(), 1);
        assertEquals(contactList.get(0).getPhone(), "9123456789");
    }

    @Test
    public void addContactTest() {
        Contact contact = new Contact();
        contact.setFirstName("Michael");
        contact.setLastName("Boyarskiy");
        contact.setPhone("0987654321");
        ContactValidation validation = phoneBookController.addContact(contact);
        List<Contact> contactList = phoneBookController.getFilteredContacts("Michael");

        assertTrue(validation.isValid());
        assertEquals(contactList.size(), 1);
        assertEquals(contactList.get(0).getLastName(), "Boyarskiy");
    }

    @Test
    public void deleteContactsTest() {
        ContactsDeletion deletion = phoneBookController.deleteContacts(Arrays.asList(1, 2));
        assertEquals(deletion.getDeleteNumber(), 2);
    }
}