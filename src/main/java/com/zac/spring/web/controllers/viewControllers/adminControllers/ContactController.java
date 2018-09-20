package com.zac.spring.web.controllers.viewControllers.adminControllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zac.spring.entity.Contact;
import com.zac.spring.service.ContactService;

/**
 * Created by Zac
 */
@Controller
@RequestMapping("/adminPage")
public class ContactController {
    private ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contacts")
    public ModelAndView showContact() {
        ModelAndView modelAndView = new ModelAndView("adminPage/contact/contacts");
        List<Contact> list = contactService.findAll();
        System.out.println(list.size());
        modelAndView.addObject("contacts", contactService.findAll());
        return modelAndView;
    }
    
}
