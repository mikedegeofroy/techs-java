package org.mikedegeofroy.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class UserDeserializer extends StdDeserializer<User> {

    public UserDeserializer() {
        this(null);
    }

    public UserDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public User deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String username = node.get("username").asText();
        String password = node.get("password").asText();
        boolean accountNonExpired = node.get("accountNonExpired").asBoolean();
        boolean accountNonLocked = node.get("accountNonLocked").asBoolean();
        boolean credentialsNonExpired = node.get("credentialsNonExpired").asBoolean();
        boolean enabled = node.get("enabled").asBoolean();

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        JsonNode authoritiesNode = node.get("authorities");
        Iterator<JsonNode> elements = authoritiesNode.elements();
        while (elements.hasNext()) {
            JsonNode authorityNode = elements.next();
            authorities.add(new SimpleGrantedAuthority(authorityNode.get("authority").asText()));
        }

        return new User(username, password, enabled, accountNonExpired, accountNonLocked, credentialsNonExpired, authorities);
    }
}
