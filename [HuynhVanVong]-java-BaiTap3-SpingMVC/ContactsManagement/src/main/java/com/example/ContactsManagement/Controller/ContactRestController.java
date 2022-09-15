package com.example.ContactsManagement.Controller;

import com.example.ContactsManagement.DTO.ContactDTO;
import com.example.ContactsManagement.Payload.response.ReCaptchaResponse;
import com.example.ContactsManagement.Service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactRestController {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }
    @Autowired
    ContactService contactService;
    @Autowired
    RestTemplate restTemplate;

    @PostMapping
    public ResponseEntity saveNewContact(@RequestBody @Valid ContactDTO contactDTO, HttpServletRequest request, HttpServletResponse response,
                                     BindingResult bindingResult) throws IOException {
        String gReCaptchaResponse = request.getParameter("g-recaptcha-response");
        if(!verifyReCAPTCHA(gReCaptchaResponse)){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(contactService.addContact(contactDTO)) ;
    }
    @GetMapping()
    public List<ContactDTO> getContacts() {
        return contactService.getAllContacts();
    }

    public boolean verifyReCAPTCHA(String captcha){
        String url= "https://www.google.com/recaptcha/api/siteverify";
        String params="?secret=6LdcZuQhAAAAAPIVm2h_HM8eFQnOtBamumHPHY_e&response="+captcha;
        String completeUrl = url+params;
        ReCaptchaResponse resp= restTemplate.postForObject(completeUrl, null, ReCaptchaResponse.class);
        return resp.isSuccess();
    }
}
