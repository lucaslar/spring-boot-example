package de.htwberlin.webtech.springbootexample.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeroWithId extends Hero {
    private Long id;

    public HeroWithId(String name, String affiliation, double heightInM, Long id) {
        super(name, affiliation, heightInM);
        this.id = id;
    }
}
