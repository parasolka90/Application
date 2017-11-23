package com.crud.tasks.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ValidatorTestSuite {

    @InjectMocks
    private TrelloValidator trelloValidator;

    @Test
    public void testValidateTrelloBoards() {
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("test1", "1", new ArrayList<>()));
        trelloBoards.add(new TrelloBoard("test2", "2", new ArrayList<>()));
        //When
        List<TrelloBoard> resultList = trelloValidator.validateTrelloBoards(trelloBoards);
        //Then
        Assert.assertEquals(2, resultList.size());
    }
}
