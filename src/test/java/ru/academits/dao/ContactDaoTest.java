package ru.academits.dao;

import org.junit.Test;
import ru.academits.model.Contact;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ContactDaoTest {
    private ContactDao contactDao = new ContactDao();

    @Test
    public void getAllContactsTest() {
        List<Contact> contactList = contactDao.getAllContacts();
        assertEquals(contactList.size(), 2);
        assertEquals(contactList.get(0).getPhone(), "9123456789");
        assertEquals(contactList.get(1).getPhone(), "9131234567");
    }

    @Test
    public void getFilteredContactsTest() {
        List<Contact> contactList = contactDao.getFilteredContacts("913");
        assertEquals(contactList.size(), 1);
        assertEquals(contactList.get(0).getPhone(), "9131234567");
    }

    @Test
    public void addTest() {
        Contact contact = new Contact();
        contact.setFirstName("Jim");
        contact.setLastName("Morrison");
        contact.setPhone("999-000");
        contactDao.add(contact);
        List<Contact> contactList = contactDao.getFilteredContacts("000");

        assertEquals(contactList.size(), 1);
        assertEquals(contactList.get(0).getLastName(), "Morrison");
    }

    @Test
    public void deleteContactsTest() {
        int delCount = contactDao.deleteContacts(Arrays.asList(1, 2, 3));
        assertEquals(delCount, 2);
    }

    @Test
    public void deleteContactsZeroDeletedTest() {
        int delCount = contactDao.deleteContacts(Arrays.asList(777, -1000));
        assertEquals(delCount, 0);
    }

    @Test
    public void deleteContactTest() {
        contactDao.deleteContact(0);
        List<Contact> contactList = contactDao.getAllContacts();
        assertEquals(contactList.size(), 1);
    }

    @Test
    public void getContactsCountTest() {
        assertEquals(contactDao.getContactsCount(), 2);
    }
}