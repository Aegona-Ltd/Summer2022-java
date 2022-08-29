package com.example.repository;

import com.example.domain.contacts.document.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsMongoRepository extends MongoRepository<Contact, Integer> {
}
