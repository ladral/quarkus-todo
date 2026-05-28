package application;

import application.services.todo.model.CreateToDoRequestDto;
import application.services.todo.model.CreateToDoResponseDto;
import infrastructure.NotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import repository.TodoRepository;

import java.util.List;


@ApplicationScoped
public class ToDoService {

    @Inject
    TodoRepository todoRepository;

    public CreateToDoResponseDto createTodo(CreateToDoRequestDto createToDoRequestDto) {
        return todoRepository.save(createToDoRequestDto.description());
    }

    public List<CreateToDoResponseDto> getTodos(String search){
        if (search == null){
            return todoRepository.findAll();
        }
        return todoRepository.findByDescription(search);
    }

    public CreateToDoResponseDto getTodosById(Long id) throws NotFoundException{
        return todoRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public CreateToDoResponseDto updateTodo(Long id, String description) throws NotFoundException {
        return todoRepository.update(id, description);
    }

    public boolean deleteTodo(Long id) {
        return todoRepository.deleteById(id);
    }
}
