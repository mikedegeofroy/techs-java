package org.mikedegeofroy.controllers;

import org.mikedegeofroy.contracts.OwnerService;
import org.mikedegeofroy.dtos.CatDto;
import org.mikedegeofroy.dtos.OwnerDto;
import org.mikedegeofroy.errors.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping("/owners")
    public ResponseEntity<?> postOwner(@RequestBody OwnerDto ownerDto) {
        ownerService.postOwner(ownerDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/owners/{id}")
    public ResponseEntity<?> postOwner(@PathVariable Integer id) {
        ownerService.deleteOwnerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/owners/{id}/cats")
    public ResponseEntity<?> postOwner(@PathVariable Integer id, @RequestBody CatDto catDto) {
        try {
            ownerService.addCatToOwner(id, catDto);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/owners/{id}/cats/assign/{cat_id}")
    public ResponseEntity<?> postOwner(@PathVariable Integer id, @PathVariable Integer cat_id) {
        try {
            ownerService.assignCatToOwner(id, cat_id);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/owners")
    public ResponseEntity<List<OwnerDto>> getOwners() {
        return new ResponseEntity<>(ownerService.getOwners(), HttpStatus.OK);
    }

    @GetMapping("/owners/{id}")
    public ResponseEntity<OwnerDto> getOwners(@PathVariable Integer id) {
        OwnerDto owner;
        try {
            owner = ownerService.getOwnerById(id);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(owner, HttpStatus.OK);
    }
}
