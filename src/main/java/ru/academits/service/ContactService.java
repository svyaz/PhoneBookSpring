package ru.academits.service;

import org.springframework.stereotype.Service;
import ru.academits.dao.ContactDao;
import ru.academits.model.Contact;
import ru.academits.model.ContactValidation;
import ru.academits.model.ContactsDeletion;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.List;

@Service
public class ContactService {
    private static final Logger logger = LogManager.getLogger(ContactService.class);
    private final ContactDao contactDao;

    public ContactService(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    private boolean isExistContactWithPhone(String phone) {
        List<Contact> contactList = contactDao.getAllContacts();
        for (Contact contact : contactList) {
            if (contact.getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    private ContactValidation validateContact(Contact contact) {
        ContactValidation contactValidation = new ContactValidation();
        contactValidation.setValid(true);
        if (contact.getFirstName().isEmpty()) {
            contactValidation.setValid(false);
            contactValidation.setError("Поле Имя должно быть заполнено.");
            logger.info("Called validateContact: firstName empty.");
            return contactValidation;
        }

        if (contact.getLastName().isEmpty()) {
            contactValidation.setValid(false);
            contactValidation.setError("Поле Фамилия должно быть заполнено.");
            logger.info("validateContact: lastName empty.");
            return contactValidation;
        }

        if (contact.getPhone().isEmpty()) {
            contactValidation.setValid(false);
            contactValidation.setError("Поле Телефон должно быть заполнено.");
            logger.info("validateContact: phone empty.");
            return contactValidation;
        }

        if (isExistContactWithPhone(contact.getPhone())) {
            contactValidation.setValid(false);
            contactValidation.setError("Номер телефона не должен дублировать другие номера в телефонной книге.");
            logger.info("validateContact: this phone already exists: " + contact.getPhone());
            return contactValidation;
        }

        logger.info("validateContact: contact validation successful.");
        return contactValidation;
    }

    public ContactValidation addContact(Contact contact) {
        ContactValidation contactValidation = validateContact(contact);
        if (contactValidation.isValid()) {
            contactDao.add(contact);
        }
        return contactValidation;
    }

    public ContactsDeletion deleteContacts(List<Integer> idsList) {
        ContactsDeletion contactsDeletion = new ContactsDeletion();
        int deletedContactsNumber = contactDao.deleteContacts(idsList);
        contactsDeletion.setDeleteNumber(deletedContactsNumber);

        if (deletedContactsNumber == 0) {
            contactsDeletion.setError("Ни одного контакта не удалено.");
        }
        return contactsDeletion;
    }

    public List<Contact> getAllContacts() {
        return contactDao.getAllContacts();
    }

    public List<Contact> getFilteredContacts(String filterString) {
        return contactDao.getFilteredContacts(filterString);
    }
}
