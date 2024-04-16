package de.htwberlin.webtech.springbootexample.web;

import de.htwberlin.webtech.springbootexample.model.Hero;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RestExampleController {

    @GetMapping("/hero")
    public ResponseEntity<List<Hero>> getHero(){
        final Hero hero = new Hero("Han Solo", "Rebellion", 1.85);
        return ResponseEntity.ok(List.of(hero));
    }
}
