package com.zac.spring.service;

import java.util.Date;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zac.spring.entity.Contact;
import com.zac.spring.repository.ContactRepository;

/**
 * Created by Zac
 */
@Service
public class ContactService {
	private ContactRepository contactRepository;

	public ContactService(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	@Cacheable("cache.allContacts")
	public List<Contact> findAll() {
		return contactRepository.findAll();
	}

	@Cacheable(value = "cache.contactByYourEmail", key = "#yourEmail", unless = "#result == null")
	public List<Contact> findByYourEmail(String email) {
		return contactRepository.findByYourEmail(email);
	}
	
	@CacheEvict(value = {"cache.allContacts" , "cache.contactByYourName", "cache.contactByYourEmail", "cache.contactByYourMessage"}, allEntries = true)
    public void createOne(Contact contact) {
    	Date nowDate = new Date();
    	contact.setCreateDate(nowDate);
    	contact.setUpdateDate(nowDate);
        contactRepository.save(contact);
    }

}
