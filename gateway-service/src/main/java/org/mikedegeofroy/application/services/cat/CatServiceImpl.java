package org.mikedegeofroy.application.services.cat;

import org.mikedegeofroy.application.contracts.CatService;
import org.mikedegeofroy.dtos.CatDto;
import org.mikedegeofroy.errors.NotFoundException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatServiceImpl implements CatService {
    private final RabbitTemplate customRabbitTemplate;

    public CatServiceImpl(@Qualifier("customRabbitTemplate") RabbitTemplate customRabbitTemplate) {
        this.customRabbitTemplate = customRabbitTemplate;
    }

    @Override
    public List<CatDto> getCats() {
        return (List<CatDto>) customRabbitTemplate.convertSendAndReceive(RabbitCatConfig.catExchange, "cat.get.all", "");
    }

    @Override
    public void removeCatById(Integer id) {
        customRabbitTemplate.convertAndSend(RabbitCatConfig.catExchange, "cat.remove.byId", id);
    }

    @Override
    public void addCat(CatDto cat) {
        customRabbitTemplate.convertAndSend(RabbitCatConfig.catExchange, "cat.add", cat);
    }

    @Override
    public void addFriendship(Integer from, Integer to) throws NotFoundException {
        customRabbitTemplate.convertAndSend(RabbitCatConfig.catExchange, "cat.friendship.add", new Object[]{from, to});
    }

    @Override
    public void removeFriendship(Integer from, Integer to) throws NotFoundException {
        customRabbitTemplate.convertAndSend(RabbitCatConfig.catExchange, "cat.friendship.remove", new Object[]{from, to});
    }

    @Override
    public List<CatDto> getFriendsById(Integer id) throws NotFoundException {
        return (List<CatDto>) customRabbitTemplate.convertSendAndReceive(RabbitCatConfig.catExchange, "cat.friendship.get", id);
    }
}
