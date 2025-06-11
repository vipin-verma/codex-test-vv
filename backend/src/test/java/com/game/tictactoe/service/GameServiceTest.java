package com.game.tictactoe.service;

import com.game.tictactoe.dto.MoveRequest;
import com.game.tictactoe.enums.Player;
import com.game.tictactoe.enums.Status;
import com.game.tictactoe.model.Game;
import com.game.tictactoe.repository.InMemoryGameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameServiceTest {
    private GameService service;

    @BeforeEach
    void setup() {
        service = new GameService(new InMemoryGameRepository());
    }

    @Test
    void testXWins() {
        Game game = service.createGame();
        service.makeMove(game.getId(), new MoveRequest(0, Player.X));
        service.makeMove(game.getId(), new MoveRequest(3, Player.O));
        service.makeMove(game.getId(), new MoveRequest(1, Player.X));
        service.makeMove(game.getId(), new MoveRequest(4, Player.O));
        service.makeMove(game.getId(), new MoveRequest(2, Player.X));
        assertEquals(Status.X_WON, service.getGame(game.getId()).getStatus());
    }

    @Test
    void testDraw() {
        Game game = service.createGame();
        int[] moves = {0,1,2,4,3,5,7,6,8};
        Player current = Player.X;
        for (int idx : moves) {
            service.makeMove(game.getId(), new MoveRequest(idx, current));
            current = current == Player.X ? Player.O : Player.X;
        }
        assertEquals(Status.DRAW, service.getGame(game.getId()).getStatus());
    }

    @Test
    void testInvalidMove() {
        Game game = service.createGame();
        service.makeMove(game.getId(), new MoveRequest(0, Player.X));
        var ex = assertThrows(RuntimeException.class,
                () -> service.makeMove(game.getId(), new MoveRequest(0, Player.O)));
        assertTrue(ex.getMessage().contains("Cell occupied"));
    }
}
