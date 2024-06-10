package de.htwberlin.webtech.springbootexample.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class HeroTest {
    @Test
    void testHeroToString() {
        final Hero hero = new Hero(1L, "Han Solo", "Rebellion", 1.85);
        final String expectation = "Hero(id=1, name=Han Solo, affiliation=Rebellion, heightInM=1.85)";
        final String result = hero.toString(); // provided via Lombok annotation
        assertEquals(expectation, result);
    }
}
