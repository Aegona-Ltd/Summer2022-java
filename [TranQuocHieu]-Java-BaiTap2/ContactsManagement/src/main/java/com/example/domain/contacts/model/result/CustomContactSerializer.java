package com.example.domain.contacts.model.result;

import com.example.domain.contacts.model.Contact;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonComponent
public class CustomContactSerializer extends StdSerializer<Contact> {

    public CustomContactSerializer() {
        this(null);
    }

    public CustomContactSerializer(Class<Contact> t) {
        super(t);
    }

    @Override
    public void serialize(Contact value, JsonGenerator gen, SerializerProvider provider) throws IOException {

        LocalDateTime localDateTime = value.getDatatime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String datatime = localDateTime.format(formatter);

        gen.writeStartObject();
        gen.writeStringField("id", String.valueOf(value.getId()));
        gen.writeStringField("datetime", datatime);
        gen.writeStringField("fullname", value.getFullname());
        gen.writeStringField("email", value.getEmail());
        gen.writeStringField("phone", value.getPhone());
        gen.writeStringField("subject", value.getSubject());
        gen.writeStringField("message", value.getMessage());
        gen.writeEndObject();
    }
}
