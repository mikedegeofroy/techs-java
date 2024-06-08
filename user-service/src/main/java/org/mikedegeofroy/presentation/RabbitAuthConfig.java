package org.mikedegeofroy.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.mikedegeofroy.serializers.UserDeserializer;
import org.mikedegeofroy.serializers.UserSerializer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;

@Configuration
public class RabbitAuthConfig {
    static final String loginQueue = "loginQueue";
    static final String verifyQueue = "verifyQueue";
    static final String authExchange = "authExchange";
    static final String queryUserQueue = "queryUserQueue";

    @Bean
    public Queue loginQueue() {
        return new Queue(loginQueue, false);
    }

    @Bean
    public Queue verifyQueue() {
        return new Queue(verifyQueue, false);
    }

    @Bean
    public Queue queryUserQueue() {
        return new Queue(queryUserQueue, false);
    }

    @Bean
    public Exchange authExchange() {
        return new TopicExchange(authExchange, false, false);
    }

    @Bean
    public Binding loginBinding() {
        return BindingBuilder.bind(loginQueue()).to(authExchange()).with("auth.login.#").noargs();
    }

    @Bean
    public Binding verifyBinding() {
        return BindingBuilder.bind(verifyQueue()).to(authExchange()).with("auth.verify.#").noargs();
    }

    @Bean
    public Binding queryUserBinding() {
        return BindingBuilder.bind(queryUserQueue()).to(authExchange()).with("auth.queryUser.#").noargs();
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        BasicPolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfSubType("org.mikedegeofroy.dtos")
                .allowIfSubType("java.util")
                .allowIfSubType("org.springframework.security.core")
                .build();
        mapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);

        SimpleModule module = new SimpleModule();
        module.addSerializer(User.class, new UserSerializer());
        module.addDeserializer(User.class, new UserDeserializer());
        mapper.registerModule(module);

        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter(mapper);
        return converter;
    }

    @Bean
    public RabbitTemplate customRabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
