package com.example.todo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@CrossOrigin
@RequestMapping("/api/todos")
public class TodoController {
    private final Map<Long, TodoItem> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @GetMapping
    public Collection<TodoItem> getAll() {
        return store.values();
    }

    @PostMapping
    public TodoItem create(@RequestBody TodoItem item) {
        long id = idGenerator.getAndIncrement();
        item.setId(id);
        store.put(id, item);
        return item;
    }

    @PutMapping("/{id}")
    public TodoItem update(@PathVariable Long id, @RequestBody TodoItem item) {
        item.setId(id);
        store.put(id, item);
        return item;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        store.remove(id);
    }
}
