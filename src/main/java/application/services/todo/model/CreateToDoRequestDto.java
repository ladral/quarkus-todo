package application.services.todo.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateToDoRequestDto(
        @NotNull(message = "description must not be null")
        @NotEmpty(message = "description must not be empty")
        @Size(max = 200)
        String description
) {
}
