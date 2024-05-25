package org.mikedegeofroy.presentation;

import org.mikedegeofroy.application.contracts.CatService;
import org.mikedegeofroy.application.dtos.CatDto;
import org.mikedegeofroy.errors.NotFoundException;
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
    public ResponseEntity<?> removeCat(@PathVariable Integer id) {
        catService.removeCatById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/cats/{id}/friends")
    public ResponseEntity<List<CatDto>> getFriends(@PathVariable Integer id) {
        List<CatDto> friends;

        try {
            friends = catService.getFriendsById(id);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(friends, HttpStatus.OK);
    }

    @PostMapping("/cats/friendship")
    public ResponseEntity<?> addFriendship(@RequestParam Integer from, @RequestParam Integer to) {
        try {
            catService.addFriendship(from, to);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/cats/friendship")
    public ResponseEntity<?> removeFriendship(@RequestParam Integer from, @RequestParam Integer to) {
        try {
            catService.removeFriendship(from, to);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
