package ru.academits.dao;

import org.springframework.stereotype.Repository;
import ru.academits.model.Contact;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class ContactDao {
    private static final Logger logger = LogManager.getLogger(ContactDao.class);

    private List<Contact> contactList = new ArrayList<>();
    private AtomicInteger idSequence = new AtomicInteger(0);

    public ContactDao() {
        Contact contact1 = new Contact();
        contact1.setId(getNewId());
        contact1.setFirstName("Иван");
        contact1.setLastName("Иванов");
        contact1.setPhone("9123456789");
        contactList.add(contact1);

        Contact contact2 = new Contact();
        contact2.setId(getNewId());
        contact2.setFirstName("Василий");
        contact2.setLastName("Чапаев");
        contact2.setPhone("9131234567");
        contactList.add(contact2);
    }

    private int getNewId() {
        return idSequence.addAndGet(1);
    }

    public List<Contact> getAllContacts() {
        logger.info("getAllContacts.");
        return contactList;
    }

    /**
     * Get filtered contacts list where firstName or lastName or phone contains
     * specified string.
     *
     * @param filterString - string to filter with.
     * @return filtered contacts list.
     */
    public List<Contact> getFilteredContacts(String filterString) {
        String lowerCaseFilterString = filterString.toLowerCase();
        logger.info("getFilteredContacts(" + filterString + ")");
        return contactList
                .stream()
                .filter(c -> (c.getFirstName().toLowerCase().contains(lowerCaseFilterString) ||
                        c.getLastName().toLowerCase().contains(lowerCaseFilterString) ||
                        c.getPhone().toLowerCase().contains(lowerCaseFilterString)))
                .collect(Collectors.toList());
    }

    public void add(Contact contact) {
        contact.setId(getNewId());
        logger.info("add(" + contact + ")");
        contactList.add(contact);
    }

    /**
     * Deletes all contacts in contactList which id contains in specified idsList.
     *
     * @param idsList list of contacts identifier to delete.
     * @return number of deleted contacts.
     */
    public int deleteContacts(List<Integer> idsList) {
        int sizeBefore = contactList.size();

        contactList = contactList.stream()
                .filter(c -> !idsList.contains(c.getId()))
                .collect(Collectors.toList());

        logger.info("deleteContacts: deleted contacts: " + (sizeBefore - contactList.size()));
        return sizeBefore - contactList.size();
    }

    /**
     * from list with
     * Deletes the contact at the specified position in this contactList.
     *
     * @param index specified position in the contactList.
     */
    public void deleteContact(int index) {
        Contact deletedContact = contactList.remove(index);
        logger.info("deleteContact: " + deletedContact.toString());
    }

    /**
     * Returns number of contacts in contactList.
     *
     * @return Number of contacts in contactList.
     */
    public int getContactsCount() {
        return contactList.size();
    }
}
