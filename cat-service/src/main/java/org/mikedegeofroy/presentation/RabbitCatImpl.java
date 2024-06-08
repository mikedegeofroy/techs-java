package org.mikedegeofroy.presentation;

import org.mikedegeofroy.application.contracts.CatService;
import org.mikedegeofroy.dtos.CatDto;
import org.mikedegeofroy.errors.NotFoundException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RabbitCatImpl {
    private final RabbitTemplate customRabbitTemplate;
    private final CatService catService;

    public RabbitCatImpl(@Qualifier("customRabbitTemplate") RabbitTemplate customRabbitTemplate, CatService catService) {
        this.customRabbitTemplate = customRabbitTemplate;
        this.catService = catService;
    }

    @RabbitListener(queues = "getCatsQueue")
    public List<CatDto> getCats() {
        return catService.getCats();
    }

    @RabbitListener(queues = "removeCatQueue")
    public void removeCatById(Integer id) {
        catService.removeCatById(id);
    }

    @RabbitListener(queues = "addCatQueue")
    public void addCat(CatDto cat) {
        catService.addCat(cat);
    }

    @RabbitListener(queues = "addFriendshipQueue")
    public void addFriendship(Integer from, Integer to) {
        try {
            catService.addFriendship(from, to);
        } catch (NotFoundException e) {
            return;
        }
    }

    @RabbitListener(queues = "removeFriendshipQueue")
    public void removeFriendship(Integer from, Integer to) {
        try {
            catService.removeFriendship(from, to);
        } catch (NotFoundException e) {
            return;
        }
    }

    @RabbitListener(queues = "getFriendsByIdQueue")
    public List<CatDto> getFriendsById(Integer id) {
        try {
            return catService.getFriendsById(id);
        } catch (NotFoundException e) {
            return null;
        }
    }
}

