package repository;

import application.services.todo.model.CreateToDoResponseDto;
import infrastructure.NotFoundException;
import java.util.List;
import java.util.Optional;

public interface TodoRepository {
    List<CreateToDoResponseDto> findAll();
    List<CreateToDoResponseDto> findByDescription(String description);
    Optional<CreateToDoResponseDto> findById(Long id);
    CreateToDoResponseDto save(String description);
    CreateToDoResponseDto update(Long id, String description) throws NotFoundException;
    boolean deleteById(Long id);
}
