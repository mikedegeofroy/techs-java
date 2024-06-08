package org.mikedegeofroy.application.services.cat;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitCatConfig {
    static final String getCatsQueue = "getCatsQueue";
    static final String removeCatQueue = "removeCatQueue";
    static final String addCatQueue = "addCatQueue";
    static final String addFriendshipQueue = "addFriendshipQueue";
    static final String removeFriendshipQueue = "removeFriendshipQueue";
    static final String getFriendsByIdQueue = "getFriendsByIdQueue";
    static final String catExchange = "catExchange";

    @Bean
    public Queue getCatsQueue() {
        return new Queue(getCatsQueue, false);
    }

    @Bean
    public Queue removeCatQueue() {
        return new Queue(removeCatQueue, false);
    }

    @Bean
    public Queue addCatQueue() {
        return new Queue(addCatQueue, false);
    }

    @Bean
    public Queue addFriendshipQueue() {
        return new Queue(addFriendshipQueue, false);
    }

    @Bean
    public Queue removeFriendshipQueue() {
        return new Queue(removeFriendshipQueue, false);
    }

    @Bean
    public Queue getFriendsByIdQueue() {
        return new Queue(getFriendsByIdQueue, false);
    }

    @Bean
    public Exchange catExchange() {
        return new TopicExchange(catExchange, false, false);
    }

    @Bean
    public Binding getCatsBinding() {
        return BindingBuilder.bind(getCatsQueue()).to(catExchange()).with("cat.get.#").noargs();
    }

    @Bean
    public Binding removeCatBinding() {
        return BindingBuilder.bind(removeCatQueue()).to(catExchange()).with("cat.remove.#").noargs();
    }

    @Bean
    public Binding addCatBinding() {
        return BindingBuilder.bind(addCatQueue()).to(catExchange()).with("cat.add.#").noargs();
    }

    @Bean
    public Binding addFriendshipBinding() {
        return BindingBuilder.bind(addFriendshipQueue()).to(catExchange()).with("cat.friendship.add.#").noargs();
    }

    @Bean
    public Binding removeFriendshipBinding() {
        return BindingBuilder.bind(removeFriendshipQueue()).to(catExchange()).with("cat.friendship.remove.#").noargs();
    }

    @Bean
    public Binding getFriendsByIdBinding() {
        return BindingBuilder.bind(getFriendsByIdQueue()).to(catExchange()).with("cat.friendship.get.#").noargs();
    }
}