package com.example.controller.contact;

import com.example.domain.contacts.service.ContactService;
import com.example.domain.restresult.RestResult;
import com.example.domain.contacts.model.ContactDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
public class ContactRestController {

    @Autowired
    private ContactService service;

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping("/contact")
    public ResponseEntity<?> listContact(@RequestParam (value = "page",defaultValue = "1",required = false) Integer page,
                                                         @RequestParam (value = "size",defaultValue = "5",required = false) Integer size) {
        return ResponseEntity.ok().body(service.listContact(page , size));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping("/contact/{id:.+}")
    public ResponseEntity<?> getContact(@PathVariable Integer id) throws JsonProcessingException {
        return ResponseEntity.ok().body(service.getContact(id));
    }

    @PostMapping("/auth/contact")
    public ResponseEntity<?> addContact(@RequestBody @Valid ContactDTO form, BindingResult bindingResult) {
        return ResponseEntity.ok().body(service.addContact(form, bindingResult));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @DeleteMapping("/contact/{id:.+}")
    public ResponseEntity<RestResult> deleteContact(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.deleteContact(id));
    }

    @GetMapping("/auth/down-file/{id:.+}")
    public void downloadFile(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
        File file = service.getFileById(id);
        response.setContentType("application/octet-stream");
        String headerKey = HttpHeaders.CONTENT_DISPOSITION;
        String headerValue = "attachment; filename=" + file.getName();
        response.setHeader(headerKey, headerValue);
        ServletOutputStream outputStream = response.getOutputStream();
        Path path = Paths.get(file.getAbsolutePath());
        outputStream.write(Files.readAllBytes(path));
    }

    @PostMapping("/auth/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("id") Integer id, @RequestParam MultipartFile file) throws IOException {
        String[] filenamelist = file.getOriginalFilename().split("[.]");
        String fileName = "contact_" + id + "." + filenamelist[1];
        File file1 = new File("src/main/resources/file/upload/" + fileName);
        try(FileOutputStream fos = new FileOutputStream(file1)) {
            fos.write(file.getBytes());
            service.addFileNameById(id, fileName);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok("File uploaded successfully.");
    }
}
