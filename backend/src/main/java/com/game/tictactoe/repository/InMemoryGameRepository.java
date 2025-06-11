package com.game.tictactoe.repository;

import com.game.tictactoe.model.Game;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryGameRepository {
    private final Map<UUID, Game> store = new ConcurrentHashMap<>();

    public void save(Game game) {
        store.put(game.getId(), game);
    }

    public Game find(UUID id) {
        return store.get(id);
    }
}
