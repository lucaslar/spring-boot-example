package de.htwberlin.webtech.springbootexample.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String affiliation;
    private double heightInM;

    public Hero(String name, String affiliation, double heightInM) {
        this.name = name;
        this.affiliation = affiliation;
        this.heightInM = heightInM;
    }
}
