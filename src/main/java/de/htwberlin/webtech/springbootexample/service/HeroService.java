package de.htwberlin.webtech.springbootexample.service;

import de.htwberlin.webtech.springbootexample.model.Hero;
import de.htwberlin.webtech.springbootexample.model.HeroWithId;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class HeroService {

    // in a real application, this would be a DB ... and you should have an additional persistence layer:

    private final HashMap<Long, HeroWithId> data = new HashMap<>() {{
        put(1L, new HeroWithId("Han Solo", "Rebellion", 1.85, 1L));
        put(2L, new HeroWithId("Chewbacca", "Rebellion", 2.2, 2L));
        put(3L, new HeroWithId("R2-D2", "Rebellion", 1, 3L));
        put(4L, new HeroWithId("C-3PO", "Rebellion", 1.75, 4L));
    }};
    private long currentId = data.size() + 1;

    public HeroWithId getHero(Long id) {
        return data.get(id);
    }

    public List<HeroWithId> getHeroes() {
        return data.values().stream().toList();
    }

    public HeroWithId addHero(final Hero hero) {
        final long id = currentId++;
        final HeroWithId heroWithId = new HeroWithId(hero.getName(), hero.getAffiliation(), hero.getHeightInM(), id);
        data.put(id, heroWithId);
        return heroWithId;
    }

    public HeroWithId editHero(final Long id, final Hero hero) {
        if (!data.containsKey(id)) return null;
        final HeroWithId heroToEdit = data.get(id);
        heroToEdit.setName(hero.getName());
        heroToEdit.setAffiliation(hero.getAffiliation());
        heroToEdit.setHeightInM(hero.getHeightInM());
        return heroToEdit;
    }

    public boolean removeHero(final Long id) {
        return data.remove(id) != null;
    }
}
