package org.mikedegeofroy.application.services.owner;

import org.mikedegeofroy.application.contracts.OwnerService;
import org.mikedegeofroy.dtos.OwnerDto;
import org.mikedegeofroy.errors.NotFoundException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {
    private final RabbitTemplate customRabbitTemplate;

    public OwnerServiceImpl(@Qualifier("customRabbitTemplate") RabbitTemplate customRabbitTemplate) {
        this.customRabbitTemplate = customRabbitTemplate;
    }

    @Override
    public void postOwner(OwnerDto ownerDto) {
        customRabbitTemplate.convertAndSend(RabbitOwnerConfig.ownerExchange, "owner.post", ownerDto);
    }

    @Override
    public List<OwnerDto> getOwners() {
        return (List<OwnerDto>) customRabbitTemplate.convertSendAndReceive(RabbitOwnerConfig.ownerExchange, "owner.get.all", "");
    }

    @Override
    public OwnerDto getOwnerById(Integer id) throws NotFoundException {
        return (OwnerDto) customRabbitTemplate.convertSendAndReceive(RabbitOwnerConfig.ownerExchange, "owner.get.byId", id);
    }

    @Override
    public void deleteOwnerById(Integer id) {
        customRabbitTemplate.convertAndSend(RabbitOwnerConfig.ownerExchange, "owner.delete.byId", id);
    }

//    @Override
//    public List<CatDto> getCatsByOwnerId(Integer id) throws NotFoundException {
//        return (List<CatDto>) customRabbitTemplate.convertSendAndReceive(RabbitOwnerConfig.ownerExchange, "owner.get.cats", id);
//    }
//    @Override
//    public void addCatToOwner(Integer id, CatDto catDto) {
//        customRabbitTemplate.convertAndSend(RabbitOwnerConfig.ownerExchange, "owner.post.cat", new Object[]{id, catDto});
//    }
//
//    @Override
//    public void assignCatToOwner(Integer id, Integer catId) {
//        customRabbitTemplate.convertAndSend(RabbitOwnerConfig.ownerExchange, "owner.assign.cat", new Object[]{id, catId});
//    }
}
