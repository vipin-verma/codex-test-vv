package com.game.tictactoe.service;

import com.game.tictactoe.dto.MoveRequest;
import com.game.tictactoe.enums.Player;
import com.game.tictactoe.enums.Status;
import com.game.tictactoe.model.Game;
import com.game.tictactoe.repository.InMemoryGameRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@Service
public class GameService {
    private static final List<int[]> WINNING_COMBINATIONS = List.of(
            new int[]{0,1,2}, new int[]{3,4,5}, new int[]{6,7,8},
            new int[]{0,3,6}, new int[]{1,4,7}, new int[]{2,5,8},
            new int[]{0,4,8}, new int[]{2,4,6}
    );

    private final InMemoryGameRepository repository;

    public GameService(InMemoryGameRepository repository) {
        this.repository = repository;
    }

    public Game createGame() {
        Game game = Game.builder()
                .id(UUID.randomUUID())
                .board(new Character[9])
                .nextTurn(Player.X)
                .status(Status.IN_PROGRESS)
                .createdAt(Instant.now())
                .build();
        repository.save(game);
        return game;
    }

    public Game getGame(UUID id) {
        Game game = repository.find(id);
        if (game == null) {
            throw new ResponseStatusException(NOT_FOUND, "Game not found");
        }
        return game;
    }

    public Game makeMove(UUID id, MoveRequest move) {
        Game game = getGame(id);
        if (game.getStatus() != Status.IN_PROGRESS) {
            throw new ResponseStatusException(BAD_REQUEST, "Game finished");
        }
        if (game.getNextTurn() != move.player()) {
            throw new ResponseStatusException(BAD_REQUEST, "Not player turn");
        }
        if (game.getBoard()[move.index()] != null) {
            throw new ResponseStatusException(BAD_REQUEST, "Cell occupied");
        }
        game.getBoard()[move.index()] = move.player().name().charAt(0);
        updateStatus(game);
        game.setNextTurn(game.getNextTurn() == Player.X ? Player.O : Player.X);
        return game;
    }

    private void updateStatus(Game game) {
        Character[] b = game.getBoard();
        for (int[] combo : WINNING_COMBINATIONS) {
            Character c1 = b[combo[0]];
            if (c1 != null && c1.equals(b[combo[1]]) && c1.equals(b[combo[2]])) {
                game.setStatus(c1 == 'X' ? Status.X_WON : Status.O_WON);
                return;
            }
        }
        if (Arrays.stream(b).noneMatch(c -> c == null)) {
            game.setStatus(Status.DRAW);
        }
    }
}
