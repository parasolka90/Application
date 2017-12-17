package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/v1/tasks")
public class TaskController {
    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper mapper;

    @GetMapping
    public List<TaskDto> getTasks() {
        return  mapper.mapToTaskDtoList(service.getAllTasks());
    }

    @GetMapping( value = "/{id}")
    public TaskDto getTask(@PathVariable Long id) throws TaskNotFoundException {
        return mapper.mapToTaskDto(service.getTaskId(id).orElseThrow(TaskNotFoundException::new));
    }
    @PostMapping
    public void createTask(@RequestBody TaskDto taskDto) {
        service.saveTask(mapper.mapToTask(taskDto));
    }
    @PutMapping()
    public TaskDto updateTask(@RequestBody TaskDto taskDto){
        return mapper.mapToTaskDto(service.saveTask(mapper.mapToTask(taskDto)));
    }
    @DeleteMapping( value = "/{id}")
    public void deleteTask(@PathVariable Long id){
         service.deleteTask(id);
    }
}
