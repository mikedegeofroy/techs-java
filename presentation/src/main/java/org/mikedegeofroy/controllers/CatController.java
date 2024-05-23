package org.mikedegeofroy.controllers;

import org.mikedegeofroy.contracts.CatService;
import org.mikedegeofroy.dtos.CatDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CatController {

    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }


    @GetMapping("/cats")
    public ResponseEntity<List<CatDto>> getCats() {
        return new ResponseEntity<>(catService.getCats(), HttpStatus.OK);
    }

    @PostMapping("/cats")
    public ResponseEntity<CatDto> addCat(@RequestBody CatDto catDto) {
        catService.addCat(catDto);
        return new ResponseEntity<>(catDto, HttpStatus.OK);
    }

    @DeleteMapping("/cats/{id}")
    public ResponseEntity removeCat(@PathVariable Integer id) {
        catService.removeCatById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/cats/{id}/friends")
    public ResponseEntity<List<CatDto>> getFriends(@PathVariable Integer id) {
        var friends = catService.getFriendsById(id);
        return new ResponseEntity<>(friends, HttpStatus.OK);
    }

    @PostMapping("/cats/friendship")
    public ResponseEntity addFriendship(@RequestParam Integer from, @RequestParam Integer to) {
        catService.addFriendship(from, to);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/cats/friendship")
    public ResponseEntity removeFriendship(@RequestParam Integer from, @RequestParam Integer to) {
        catService.removeFriendship(from, to);
        return new ResponseEntity(HttpStatus.OK);
    }
}
