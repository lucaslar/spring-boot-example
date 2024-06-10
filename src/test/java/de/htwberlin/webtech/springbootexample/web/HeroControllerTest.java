package de.htwberlin.webtech.springbootexample.web;

import de.htwberlin.webtech.springbootexample.model.Hero;
import de.htwberlin.webtech.springbootexample.service.HeroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.equalTo;

@WebMvcTest(HeroController.class)
public class HeroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HeroService service;

    @BeforeEach
    void setUpMockRepository() {
        final Hero hanSolo = new Hero(1L, "Han Solo", "Rebellion", 1.85);
        when(service.getHero(1L)).thenReturn(Optional.of(hanSolo));
    }

    @Test
    void testGetHeroById() throws Exception {
        final String expectation = "{\"id\":1,\"name\":\"Han Solo\",\"affiliation\":\"Rebellion\",\"heightInM\":1.85}";
        this.mockMvc.perform(get("/heroes/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(equalTo(expectation)));
    }
}
