package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/task")
public class TaskController {
    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper mapper;

    @GetMapping( value = "getTasks")
    public List<TaskDto> getTasks() {
        return  mapper.mapToTaskDtoList(service.getAllTasks());
    }

    @GetMapping( value = "getTask")
    public TaskDto getTask(@RequestParam Long taskId) throws TaskNotFoundException {
        return mapper.mapToTaskDto(service.getTaskId(taskId).orElseThrow(TaskNotFoundException::new));
    }
    @PostMapping( value = "createTask")
    public void createTask(@RequestBody TaskDto taskDto) {
        service.saveTask(mapper.mapToTask(taskDto));
    }
    @PutMapping( value = "updateTask")
    public TaskDto updateTask(@RequestBody TaskDto taskDto){
        return mapper.mapToTaskDto(service.saveTask(mapper.mapToTask(taskDto)));
    }
    @DeleteMapping( value = "deleteTask")
    public void deleteTask(@RequestParam Long taskId){
         service.deleteTask(taskId);
    }
}
