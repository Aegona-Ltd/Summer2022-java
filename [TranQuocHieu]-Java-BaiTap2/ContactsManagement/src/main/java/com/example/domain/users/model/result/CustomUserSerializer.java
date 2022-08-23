package com.example.domain.users.model.result;

import com.example.domain.contacts.model.Contact;
import com.example.domain.role.model.Role;
import com.example.domain.users.model.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class CustomUserSerializer extends StdSerializer<User> {
    public CustomUserSerializer() {
        this(null);
    }

    public CustomUserSerializer(Class<User> t) {
        super(t);
    }

    @Override
    public void serialize(User value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", value.getId());
        gen.writeStringField("name", value.getName());
        gen.writeStringField("email", value.getEmail());
        gen.writeFieldName("roles");
        gen.writeStartArray();
        for (Role role: value.getRoles()) {
            gen.writeStartObject();
            gen.writeNumberField("id", role.getId());
            gen.writeObjectField("name", role.getName());
            gen.writeEndObject();
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }
}
