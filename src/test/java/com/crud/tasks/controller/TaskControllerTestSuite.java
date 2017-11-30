package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldGetTasks() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1l, "title", "test");
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(taskDto);

        List<Task> taskList = new ArrayList<>();
        taskList.add(taskMapper.mapToTask(taskDto));

        Mockito.when(dbService.getAllTasks()).thenReturn(taskList);
        Mockito.when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskDtoList);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("title")))
                .andExpect(jsonPath("$[0].content", is("test")));
    }

    @Test
    public void shouldGetTask() throws Exception {
        //Given
        long id = 1;
        TaskDto taskDto = new TaskDto(id, "title", "test");
        Task task = new Task(id, "title", "test");

        Mockito.when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        Mockito.when(dbService.getTaskId(id)).thenReturn(Optional.ofNullable(task));
        Mockito.when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(get("/v1/task/getTask?taskId=" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("title")))
                .andExpect(jsonPath("$.content", is("test")));
    }
    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test title", "test");
        Task task = new Task(1L, "test title", "test");

        Mockito.when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        Mockito.when(taskMapper.mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(taskDto)))).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);

        //When & Then
        mockMvc.perform(post("/v1/task/createTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test title")))
                .andExpect(jsonPath("$.content", is("test")));
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test title", "test");
        Task task = new Task(1L, "test title", "test");

        Mockito.when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        Mockito.when(taskMapper.mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(taskDto)))).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);

        //When & Then
        mockMvc.perform(put("/v1/task/updateTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test title")))
                .andExpect(jsonPath("$.content", is("test")));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //Given
        long id = 1l;
        TaskDto taskDto = new TaskDto(id, "title", "test");
        Task task = new Task(id, "title", "test");

        Mockito.when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        Mockito.when(dbService.getTaskId(id)).thenReturn(Optional.ofNullable(task));
        Mockito.when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);


        //When & Then
        mockMvc.perform(delete("/v1/task/deleteTask?taskId=" + id))
                .andExpect(status().isOk());
        verify(dbService, times(1)).deleteTask(id);
    }


}