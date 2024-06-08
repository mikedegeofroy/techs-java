package org.mikedegeofroy.presentation;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitAuthConfig {
    static final String loginQueue = "loginQueue";
    static final String verifyQueue = "verifyQueue";
    static final String authExchange = "authExchange";

    @Bean
    public Queue loginQueue() {
        return new Queue(loginQueue, false);
    }

    @Bean
    public Queue verifyQueue() {
        return new Queue(verifyQueue, false);
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
}
