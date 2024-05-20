package de.htwberlin.webtech.springbootexample.web;

import de.htwberlin.webtech.springbootexample.model.Hero;
import de.htwberlin.webtech.springbootexample.model.HeroWithId;
import de.htwberlin.webtech.springbootexample.service.HeroService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/hero")
public class RestExampleController {

    private final HeroService heroService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HeroWithId>> getHero() {
        return ResponseEntity.ok(heroService.getHeroes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeroWithId> getHero(@PathVariable("id") final Long id) {
        final HeroWithId found = heroService.getHero(id);
        return found != null ? ResponseEntity.ok(found) : ResponseEntity.notFound().build();

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<URI> addHero(@Valid @RequestBody Hero body) {
        final HeroWithId createdHero = heroService.addHero(body);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/" + createdHero.getId()).build().toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HeroWithId> updateHero(@PathVariable("id") final Long id, @RequestBody Hero body) {
        final HeroWithId updatedHero = heroService.editHero(id, body);
        if (updatedHero == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(updatedHero);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteHero(@PathVariable("id") final Long id) {
        return heroService.removeHero(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
