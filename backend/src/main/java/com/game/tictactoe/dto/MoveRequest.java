package com.game.tictactoe.dto;

import com.game.tictactoe.enums.Player;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record MoveRequest(@Min(0) @Max(8) int index, @NotNull Player player) {}
