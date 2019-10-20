package ru.academits.phonebook;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.academits.model.Contact;
import ru.academits.model.ContactValidation;
import ru.academits.model.ContactsDeletion;
import ru.academits.service.ContactService;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.List;

@Controller
@RequestMapping(value = "/phonebook")
public class PhoneBookController {
    private static final Logger logger = LogManager.getLogger(PhoneBookController.class);

    private final ContactService contactService;

    public PhoneBookController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Contact> getAllContacts() {
        logger.info("Called getAllContacts");
        return contactService.getAllContacts();
    }

    @RequestMapping(value = "/get/filter", method = RequestMethod.GET)
    @ResponseBody
    public List<Contact> getFilteredContacts(@RequestParam String s) {
        // s - filter string
        logger.info("Called getFilteredContacts(" + s + ")");
        return contactService.getFilteredContacts(s);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ContactValidation addContact(@RequestBody Contact contact) {
        logger.info("Called addContact(" + contact + ")");
        return contactService.addContact(contact);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ContactsDeletion deleteContacts(@RequestBody List<Integer> idsList) {
        logger.info("Called deleteContacts(" + idsList + ")");
        return contactService.deleteContacts(idsList);
    }
}
