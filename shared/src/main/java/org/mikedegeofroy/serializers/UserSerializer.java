package org.mikedegeofroy.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.security.core.userdetails.User;

import java.io.IOException;

public class UserSerializer extends StdSerializer<User> {

    public UserSerializer() {
        this(null);
    }

    public UserSerializer(Class<User> t) {
        super(t);
    }

    @Override
    public void serialize(User user, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("username", user.getUsername());
        gen.writeStringField("password", user.getPassword());
        gen.writeObjectField("authorities", user.getAuthorities());
        gen.writeBooleanField("accountNonExpired", user.isAccountNonExpired());
        gen.writeBooleanField("accountNonLocked", user.isAccountNonLocked());
        gen.writeBooleanField("credentialsNonExpired", user.isCredentialsNonExpired());
        gen.writeBooleanField("enabled", user.isEnabled());
        gen.writeEndObject();
    }
}
