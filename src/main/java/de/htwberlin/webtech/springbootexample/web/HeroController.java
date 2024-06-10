package de.htwberlin.webtech.springbootexample.web;

import de.htwberlin.webtech.springbootexample.model.Hero;
import de.htwberlin.webtech.springbootexample.service.HeroService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/heroes")
public class HeroController {

    private final HeroService heroService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Hero>> getHeroes(@RequestParam final Optional<String> affiliation) {
        final Iterable<Hero> result = affiliation.isEmpty() || affiliation.get().isBlank()
                ? heroService.getHeroes()
                : heroService.getHeroes(affiliation.get());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hero> getHero(@PathVariable("id") final Long id) {
        final Optional<Hero> found = heroService.getHero(id);
        return found.isPresent() ? ResponseEntity.ok(found.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Hero> addHero(@Valid @RequestBody Hero body) {
        final Hero h = new Hero(body.getName(), body.getAffiliation(), body.getHeightInM());
        final Hero createdHero = heroService.addHero(h);
        return new ResponseEntity<>(createdHero, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Hero> updateHero(@PathVariable("id") final Long id, @RequestBody Hero body) {
        body.setId(id);
        final Hero updatedHero = heroService.editHero(body);
        if (updatedHero == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(updatedHero);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteHero(@PathVariable("id") final Long id) {
        return heroService.removeHero(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
