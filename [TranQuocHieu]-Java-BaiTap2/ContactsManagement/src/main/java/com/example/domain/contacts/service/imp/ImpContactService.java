package com.example.domain.contacts.service.imp;

import com.example.domain.contacts.model.Contact;
import com.example.domain.contacts.service.ContactService;
import com.example.form.ContactForm;
import com.example.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ImpContactService implements ContactService {

    @Autowired
    private ContactRepository repository;

    @Override
    public List<Contact> listContact() {
        return repository.findAll();
    }

    @Override
    public ContactForm addContact(ContactForm form) {
        String fullName = form.getFullname();
        String email = form.getEmail();
        String phone = form.getPhone();
        String sub = form.getSubject();
        String messnull = "Please enter the ";
        String regex = "^(.+)@(.+)$";
        int phoneNumber = 0;

        boolean error = false;
//        Fullname
        if (fullName.isEmpty()){
            form.setMessFullname(messnull + "Fullname");
            error = true;
        }else {
            form.setMessFullname("");
        }
//        Email
        if (email.isEmpty()){
            form.setMessEmail(messnull + "Email");
            error = true;
        }else if (!email.matches(regex)){
            form.setMessEmail("Invalid email!!");
            error = true;
        }else {
            form.setMessEmail("");
        }
//        Phone
        if (phone.isEmpty()) {
            form.setMessPhone(messnull + "Phone Number");
            error = true;
        }else if (phone.length()<10 || phone.length()>10){
            form.setMessPhone("Phone number accept length value equals 10");
            error = true;
        } else {
            try {
                phoneNumber = Integer.parseInt(form.getPhone());
                form.setMessPhone("");
            }catch (Exception e){
                form.setMessPhone("Please enter the number");
                error = true;
            }
        }

//        Subject
        if (sub.isEmpty()){
            form.setMessSubject(messnull + "Subject");
            error = true;
        }else {
            form.setMessSubject("");
        }
        if (error) {
            return form;
        }else {
//            Data-time now
            LocalDateTime myDateObj = LocalDateTime.now();

            Contact contact = new Contact(form);
            contact.setDatatime(myDateObj);
            repository.save(contact);
            return null;
        }
    }

    @Override
    public void deleteContact(Integer id) {
        repository.deleteById(id);
    }
}
