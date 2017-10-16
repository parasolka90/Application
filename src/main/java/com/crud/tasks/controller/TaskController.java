package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/task")
public class TaskController {
    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper mapper;

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks() {
        return  mapper.mapToTaskDtoList(service.getAllTasks());
    }

    @RequestMapping(method = RequestMethod.GET,value = "getTask")
    public TaskDto getTask(Long taskId) {
        return mapper.mapToTaskDto(service.getTaskId(taskId).orElseThrow(() -> new IllegalArgumentException()));
    }
    /*@RequestMapping(method = RequestMethod.DELETE,value = "deleteTask")
    public void deleteTask(String taskId) {

    }
    @RequestMapping(method = RequestMethod.POST,value = "updateTask")
    public TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto((long)1,"edited test title","test content1");
    }
    @RequestMapping(method = RequestMethod.PUT,value = "createTask")
    public void createTask(TaskDto taskDto){

    }
    */
}
