package com.game.tictactoe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.tictactoe.dto.MoveRequest;
import com.game.tictactoe.dto.NewGameResponse;
import com.game.tictactoe.enums.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void happyPath() throws Exception {
        String json = mvc.perform(post("/api/games"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        NewGameResponse resp = objectMapper.readValue(json, NewGameResponse.class);
        mvc.perform(get("/api/games/" + resp.gameId()))
                .andExpect(status().isOk());
        mvc.perform(post("/api/games/" + resp.gameId() + "/move")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new MoveRequest(0, Player.X))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.board[0]").value("X"));
    }

    @Test
    void notFound() throws Exception {
        mvc.perform(get("/api/games/" + UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }
}
