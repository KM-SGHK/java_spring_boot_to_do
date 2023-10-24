package com.firstJavaCrud.service;

import com.firstJavaCrud.entity.ToDo;
import com.firstJavaCrud.repository.ToDoRepository;
import com.firstJavaCrud.request.CreateToDoRequest;
import com.firstJavaCrud.request.UpdateToDoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    @Autowired
    ToDoRepository toDoRepository;

    public List<ToDo> getAllToDos () {
        return toDoRepository.findAll();
    }

    public ToDo createToDo (CreateToDoRequest createToDoRequest) {
        ToDo toDo = new ToDo(createToDoRequest);

        System.out.println("check toDo, ");
        System.out.println(toDo.getTaskId());

        toDo = toDoRepository.save(toDo);

        return toDo;
    }

    public ToDo updateToDo (UpdateToDoRequest updateToDoRequest) {
        System.out.println("check toDo name in put case, ");
        System.out.println(updateToDoRequest.getTaskTitle());
        ToDo toDo = toDoRepository.findById(updateToDoRequest.getTaskId()).get();

        if(updateToDoRequest.getTaskTitle() != null &&
                !updateToDoRequest.getTaskTitle().isEmpty()
        ){
            toDo.setTaskTitle(updateToDoRequest.getTaskTitle());
            toDo.setTaskBody(updateToDoRequest.getTaskBody());
        }

        toDo = toDoRepository.save(toDo);

        return toDo;
    }

    public String deleteToDo(Long id) {
        System.out.println("check id in delete case, ");
        System.out.println(id);
        toDoRepository.deleteById(id);
        return "Task " + id + " is deleted";
    }
}
