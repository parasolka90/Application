package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTestSuite {

    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        //When
        Task resultTask = taskMapper.mapToTask(taskDto);
        //Then
        Assert.assertEquals(1L,resultTask.getId().longValue());
        Assert.assertEquals("title", resultTask.getTitle());
        Assert.assertEquals("content", resultTask.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(1L, "title", "content");
        //When
        TaskDto resultTask = taskMapper.mapToTaskDto(task);
        //Then
        Assert.assertEquals(1L, resultTask.getId().longValue());
        Assert.assertEquals("title", resultTask.getTitle());
        Assert.assertEquals("content", resultTask.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "title", "content"));
        //When
        List<TaskDto> resultList = taskMapper.mapToTaskDtoList(taskList);
        //Then
        Assert.assertEquals(1L, resultList.get(0).getId().longValue());
        Assert.assertEquals("title", resultList.get(0).getTitle());
        Assert.assertEquals("content", resultList.get(0).getContent());

    }
}