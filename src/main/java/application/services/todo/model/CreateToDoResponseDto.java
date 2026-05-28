package application.services.todo.model;

import jakarta.enterprise.context.ApplicationScoped;

public record CreateToDoResponseDto(
        Long id,
        String description
) {
}
