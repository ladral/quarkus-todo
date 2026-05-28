package api;

import application.ToDoService;
import application.services.todo.model.CreateToDoRequestDto;
import application.services.todo.model.CreateToDoResponseDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/api/todos")
public class ToDoResource {

    @Inject
    ToDoService toDoService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CreateToDoResponseDto createTodo(CreateToDoRequestDto createToDoRequestDto) {
        return toDoService.createTodo(createToDoRequestDto);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CreateToDoResponseDto> getTodo(@QueryParam("search") String search) {
        return toDoService.getTodos(search);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CreateToDoResponseDto getTodoById(@PathParam("id") Long id) {
        return toDoService.getTodosById(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CreateToDoResponseDto updateTodo(@PathParam("id")Long id, CreateToDoRequestDto createToDoRequestDto) {
        return toDoService.updateTodo(id, createToDoRequestDto.description());
    }

    @DELETE
    @Path("/{id}")
    public boolean deleteTodo(@PathParam("id") Long id) {
        return toDoService.deleteTodo(id);
    }
}
