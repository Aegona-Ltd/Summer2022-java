package com.example.repository;

import com.example.domain.contacts.document.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactsMongoRepository extends MongoRepository<Contact, Integer> {
    @Query(value = "{'email': ?0}")
    List<Contact> findByEmail(String email);
}
