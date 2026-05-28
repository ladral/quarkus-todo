package repository;

import application.services.todo.model.CreateToDoResponseDto;
import infrastructure.NotFoundException;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class InMemoryToDoRepository implements TodoRepository{

    private final List<CreateToDoResponseDto> todos = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @Override
    public List<CreateToDoResponseDto> findAll() {
        return this.todos;
    }

    @Override
    public  List<CreateToDoResponseDto> findByDescription(String description) {
        return this.todos.stream().filter(todo -> todo.description().contains(description)).toList();
    }

    @Override
    public Optional<CreateToDoResponseDto> findById(Long id) {
        return todos.stream()
                .filter(todo -> todo.id().equals(id))
                .findFirst();
    }

    @Override
    public CreateToDoResponseDto save(String description) {
        CreateToDoResponseDto newTodo = new CreateToDoResponseDto(idCounter.getAndIncrement(), description);
        todos.add(newTodo);
        return newTodo;
    }

    @Override
    public CreateToDoResponseDto update(Long id, String description) throws NotFoundException {
        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).id().equals(id)) {
                CreateToDoResponseDto updatedTodo = new CreateToDoResponseDto(id, description);
                todos.set(i, updatedTodo);
                return updatedTodo;
            }
        }
        throw new NotFoundException();
    }

    @Override
    public boolean deleteById(Long id) {
        return todos.removeIf(todo -> todo.id().equals(id));
    }
}
