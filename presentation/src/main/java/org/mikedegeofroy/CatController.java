package org.mikedegeofroy;

@RestController
public class CatController {

    @GetMapping("/cats")
    public String getCats() {
        return "Hello world";
    }
}
