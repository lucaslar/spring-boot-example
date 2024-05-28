package de.htwberlin.webtech.springbootexample.persistence;

import de.htwberlin.webtech.springbootexample.model.Hero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroRepository extends CrudRepository<Hero, Long> {
}
