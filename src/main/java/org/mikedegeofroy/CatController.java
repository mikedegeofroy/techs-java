package org.mikedegeofroy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatController {

    @GetMapping("/cats")
    public String getCats() {
        return "Hello world";
    }
}
