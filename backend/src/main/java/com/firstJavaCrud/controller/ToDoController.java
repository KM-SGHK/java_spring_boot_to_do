package com.firstJavaCrud.controller;

import com.firstJavaCrud.entity.ToDo;
import com.firstJavaCrud.request.CreateToDoRequest;
import com.firstJavaCrud.request.UpdateToDoRequest;
import com.firstJavaCrud.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("toDo")
public class ToDoController {

	@Autowired
	ToDoService toDoService;

	@GetMapping
	public String getTasks()
	{
		System.out.println("api accessed");
		return "hello world";
	}

	// About CORS Setting:
	// https://stackoverflow.com/questions/39623211/add-multiple-cross-origin-urls-in-spring-boot
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/getAllToDos")
	public List<ToDo> getAllToDos() {
		System.out.println("second api accessed");
		return toDoService.getAllToDos();
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/createToDo")
    public ToDo createToDo (@RequestBody CreateToDoRequest createToDoRequest) {
		ToDo toDo = toDoService.createToDo(createToDoRequest);

		return toDo;
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping("/updateToDo")
	public ToDo updateToDo (@RequestBody UpdateToDoRequest updateToDoRequest) {
		ToDo toDo = toDoService.updateToDo(updateToDoRequest);

		return toDo;
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping("/deleteToDo")
	// sample route - /deleteToDo?id=2
	public String deleteToDo(@RequestParam Long id) {
		System.out.println("got delete api");
		System.out.println(id);
        return toDoService.deleteToDo(id);
	}
}
