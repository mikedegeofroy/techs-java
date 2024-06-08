package org.mikedegeofroy.presentation;

import org.mikedegeofroy.application.contracts.OwnerService;
import org.mikedegeofroy.dtos.OwnerDto;
import org.mikedegeofroy.errors.NotFoundException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RabbitOwnerImpl {
    private final OwnerService ownerService;

    @Autowired
    public RabbitOwnerImpl(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RabbitListener(queues = RabbitOwnerConfig.postOwnerQueue)
    public void postOwner(OwnerDto ownerDto) {
        ownerService.postOwner(ownerDto);
    }

    @RabbitListener(queues = RabbitOwnerConfig.deleteOwnerQueue)
    public void deleteOwnerById(Integer id) {
        ownerService.deleteOwnerById(id);
    }

    @RabbitListener(queues = RabbitOwnerConfig.getOwnersQueue)
    public List<OwnerDto> getOwners() {
        return ownerService.getOwners();
    }

    @RabbitListener(queues = RabbitOwnerConfig.getOwnerByIdQueue)
    public OwnerDto getOwnerById(Integer id) throws NotFoundException {
        return ownerService.getOwnerById(id);
    }

//    @RabbitListener(queues = RabbitOwnerConfig.getCatsByOwnerQueue)
//    public List<CatDto> getCatsByOwnerId(Integer id) throws NotFoundException {
//        return ownerService.getCatsByOwnerId(id);
//    }

//    @RabbitListener(queues = RabbitOwnerConfig.postCatToOwnerQueue)
//    public void addCatToOwner(Integer id, CatDto catDto) throws NotFoundException {
//        ownerService.addCatToOwner(id, catDto);
//    }
//
//    @RabbitListener(queues = RabbitOwnerConfig.assignCatToOwnerQueue)
//    public void assignCatToOwner(Integer id, Integer catId) throws NotFoundException {
//        ownerService.assignCatToOwner(id, catId);
//    }
}
