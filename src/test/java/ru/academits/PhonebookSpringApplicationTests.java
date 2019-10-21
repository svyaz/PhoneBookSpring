package ru.academits;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.academits.model.Contact;
import ru.academits.phonebook.PhoneBookController;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PhonebookSpringApplicationTests {
    @Autowired
    private PhoneBookController phoneBookController;

    @Test
    public void getAllContactsTest() {
        List<Contact> contactList = phoneBookController.getAllContacts();
        Assert.assertEquals(contactList.size(), 2);
        Assert.assertEquals(contactList.get(0).getPhone(), "9123456789");
        Assert.assertEquals(contactList.get(1).getPhone(), "9131234567");
    }
}
