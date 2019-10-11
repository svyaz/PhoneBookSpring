package ru.academits.phonebook;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.academits.model.Contact;
import ru.academits.model.ContactValidation;
import ru.academits.model.ContactsDeletion;
import ru.academits.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/phonebook")
public class PhoneBookController {
    private static final Logger logger = LoggerFactory.getLogger(PhoneBookController.class);

    private final ContactService contactService;

    public PhoneBookController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Contact> getAllContacts() {
        logger.info("called method getAllContacts");
        return contactService.getAllContacts();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ContactValidation addContact(@RequestBody Contact contact) {
        return contactService.addContact(contact);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ContactsDeletion deleteContacts(@RequestBody List<Integer> idsList) {
        return contactService.deleteContacts(idsList);
    }
}


