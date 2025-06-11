package com.game.tictactoe.model;

import com.game.tictactoe.enums.Player;
import com.game.tictactoe.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Game {
    private UUID id;
    private char[] board;
    private Player nextTurn;
    private Status status;
    private Instant createdAt;
}
