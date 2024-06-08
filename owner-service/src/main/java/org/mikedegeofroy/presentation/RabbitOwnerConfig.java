package org.mikedegeofroy.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitOwnerConfig {
    static final String postOwnerQueue = "postOwnerQueue";
    static final String deleteOwnerQueue = "deleteOwnerQueue";
    static final String getOwnersQueue = "getOwnersQueue";
    static final String getOwnerByIdQueue = "getOwnerByIdQueue";
    static final String getCatsByOwnerQueue = "getCatsByOwnerQueue";
    static final String postCatToOwnerQueue = "postCatToOwnerQueue";
    static final String assignCatToOwnerQueue = "assignCatToOwnerQueue";
    static final String ownerExchange = "ownerExchange";

    @Bean
    public Queue postOwnerQueue() {
        return new Queue(postOwnerQueue, false);
    }

    @Bean
    public Queue deleteOwnerQueue() {
        return new Queue(deleteOwnerQueue, false);
    }

    @Bean
    public Queue getOwnersQueue() {
        return new Queue(getOwnersQueue, false);
    }

    @Bean
    public Queue getOwnerByIdQueue() {
        return new Queue(getOwnerByIdQueue, false);
    }

    @Bean
    public Queue getCatsByOwnerQueue() {
        return new Queue(getCatsByOwnerQueue, false);
    }

    @Bean
    public Queue postCatToOwnerQueue() {
        return new Queue(postCatToOwnerQueue, false);
    }

    @Bean
    public Queue assignCatToOwnerQueue() {
        return new Queue(assignCatToOwnerQueue, false);
    }

    @Bean
    public Exchange ownerExchange() {
        return new TopicExchange(ownerExchange, false, false);
    }

    @Bean
    public Binding postOwnerBinding() {
        return BindingBuilder.bind(postOwnerQueue()).to(ownerExchange()).with("owner.post.#").noargs();
    }

    @Bean
    public Binding deleteOwnerBinding() {
        return BindingBuilder.bind(deleteOwnerQueue()).to(ownerExchange()).with("owner.delete.#").noargs();
    }

    @Bean
    public Binding getOwnersBinding() {
        return BindingBuilder.bind(getOwnersQueue()).to(ownerExchange()).with("owner.get.all.#").noargs();
    }

    @Bean
    public Binding getOwnerByIdBinding() {
        return BindingBuilder.bind(getOwnerByIdQueue()).to(ownerExchange()).with("owner.get.byId.#").noargs();
    }

    @Bean
    public Binding getCatsByOwnerBinding() {
        return BindingBuilder.bind(getCatsByOwnerQueue()).to(ownerExchange()).with("owner.get.cats.#").noargs();
    }

    @Bean
    public Binding postCatToOwnerBinding() {
        return BindingBuilder.bind(postCatToOwnerQueue()).to(ownerExchange()).with("owner.post.cat.#").noargs();
    }

    @Bean
    public Binding assignCatToOwnerBinding() {
        return BindingBuilder.bind(assignCatToOwnerQueue()).to(ownerExchange()).with("owner.assign.cat.#").noargs();
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        BasicPolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfSubType("org.mikedegeofroy.dtos")
                .allowIfSubType("java.util")
                .build();
        mapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);

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
