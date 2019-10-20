package ru.academits.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.academits.dao.ContactDao;

import java.util.Random;

@Service
@EnableScheduling
public class SchedulerService {
    private static final Logger logger = LogManager.getLogger(ContactService.class);
    private final ContactDao contactDao;

    public SchedulerService(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Scheduled(initialDelay = 5000, fixedRate = 10000)
    public void deleteRandomContact() {
        int contactsCount = contactDao.getContactsCount();

        if (contactsCount > 0) {
            logger.info("deleteRandomContact, contactsCount = " + contactsCount);
            Random random = new Random();
            int index = random.nextInt(contactsCount);
            contactDao.deleteContact(index);
            logger.info("deleteRandomContact, contact with index = " + index + " deleted.");
        } else {
            logger.info("deleteRandomContact, nothing to delete.");
        }
    }
}
