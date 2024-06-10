package de.htwberlin.webtech.springbootexample.service;

import de.htwberlin.webtech.springbootexample.model.Hero;
import de.htwberlin.webtech.springbootexample.persistence.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class HeroService {

    @Autowired
    private HeroRepository repository;

    public Optional<Hero> getHero(Long id) {
        return this.repository.findById(id);
    }

    public Iterable<Hero> getHeroes() {
        return this.repository.findAll();
    }

    public Iterable<Hero> getHeroes(final String affiliation) {
        final Iterable<Hero> data = this.repository.findAll();
        return StreamSupport.stream(data.spliterator(), false)
                .filter(h -> h.getAffiliation() != null && h.getAffiliation().equalsIgnoreCase(affiliation))
                .collect(Collectors.toSet());
    }

    public Hero addHero(final Hero hero) {
        return repository.save(hero);
    }

    public Hero editHero(final Hero hero) {
        return repository.existsById(hero.getId())
                ? repository.save(hero)
                : null;
    }

    public boolean removeHero(final Long id) {
        final boolean exists = repository.existsById(id);
        if (exists) repository.deleteById(id);
        return exists;
    }
}
