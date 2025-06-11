package com.game.tictactoe.controller;

import com.game.tictactoe.dto.MoveRequest;
import com.game.tictactoe.dto.NewGameResponse;
import com.game.tictactoe.model.Game;
import com.game.tictactoe.service.GameService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/games")
@CrossOrigin(origins = "http://localhost:5173")
public class GameController {
    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    @PostMapping
    public NewGameResponse createGame() {
        Game game = service.createGame();
        return new NewGameResponse(game.getId());
    }

    @GetMapping("/{id}")
    public Game getGame(@PathVariable UUID id) {
        return service.getGame(id);
    }

    @PostMapping("/{id}/move")
    public Game makeMove(@PathVariable UUID id, @Valid @RequestBody MoveRequest move) {
        return service.makeMove(id, move);
    }
}
