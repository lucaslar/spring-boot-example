package de.htwberlin.webtech.springbootexample.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Hero {
    private String name;
    private String affiliation;
    private double heightInM;
}
