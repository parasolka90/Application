package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper mapper;

    @Test
    public void testMapToBoardAndBoardDto() {
        //Given
        List<TrelloBoardDto> trelloBoardsDtos = new ArrayList<>();
        trelloBoardsDtos.add(new TrelloBoardDto("test id 1", "test name", new ArrayList<>()));

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("test id 1", "test name", new ArrayList<>()));

        //When
        List<TrelloBoard> result1 = mapper.mapToBoard(trelloBoardsDtos);
        List<TrelloBoardDto> result2 = mapper.mapToBoardDto(trelloBoards);

        //Then
        for(int i=0; i<trelloBoardsDtos.size(); i++) {
            Assert.assertEquals(trelloBoards.get(i).getName(), result1.get(i).getName());
            Assert.assertEquals(trelloBoards.get(i).getId(), result1.get(i).getId());
            Assert.assertEquals(trelloBoards.get(i).getLists(), result1.get(i).getLists());
        }
        for(int i=0; i<trelloBoards.size(); i++) {
            Assert.assertEquals(trelloBoardsDtos.get(i).getName(), result2.get(i).getName());
            Assert.assertEquals(trelloBoardsDtos.get(i).getId(), result2.get(i).getId());
            Assert.assertEquals(trelloBoardsDtos.get(i).getLists(), result2.get(i).getLists());
        }
    }

    @Test
    public void testMapToListAndListDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "name 1", true));
        trelloLists.add(new TrelloList("2", "name 2", false));

        List<TrelloListDto> trelloListsDtos = new ArrayList<>();
        trelloListsDtos.add(new TrelloListDto("1", "name 1", true));
        trelloListsDtos.add(new TrelloListDto("2", "name 2", false));

        //When
        List<TrelloListDto> result1 = mapper.mapToListDto(trelloLists);
        List<TrelloList> result2 = mapper.mapToList(trelloListsDtos);

        //Then
        for(int i=0; i<trelloListsDtos.size(); i++) {
            Assert.assertEquals(trelloListsDtos.get(i).getName(), result1.get(i).getName());
            Assert.assertEquals(trelloListsDtos.get(i).getId(), result1.get(i).getId());
            Assert.assertEquals(trelloListsDtos.get(i).isClosed(), result1.get(i).isClosed());
        }
        for(int i=0; i<trelloLists.size(); i++) {
            Assert.assertEquals(trelloLists.get(i).getName(), result2.get(i).getName());
            Assert.assertEquals(trelloLists.get(i).getId(), result2.get(i).getId());
            Assert.assertEquals(trelloLists.get(i).isClosed(), result2.get(i).isClosed());
        }
    }

    @Test
    public void testMapToCardAndCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("name1", "description1", "test1", "12");
        TrelloCardDto trelloCardDto = new TrelloCardDto("name1", "description1", "test1", "12");

        //When
        TrelloCard resultCard = mapper.mapToCard(trelloCardDto);
        TrelloCardDto resultCardDto = mapper.maptoCardDto(trelloCard);

        //Then
        Assert.assertEquals(trelloCard.getName(), resultCard.getName());
        Assert.assertEquals(trelloCard.getDescription(), resultCard.getDescription());
        Assert.assertEquals(trelloCard.getListId(), resultCard.getListId());
        Assert.assertEquals(trelloCard.getPos(), resultCard.getPos());

        Assert.assertEquals(trelloCardDto.getName(), resultCardDto.getName());
        Assert.assertEquals(trelloCardDto.getDescription(), resultCardDto.getDescription());
        Assert.assertEquals(trelloCardDto.getListId(), resultCardDto.getListId());
        Assert.assertEquals(trelloCardDto.getPos(), resultCardDto.getPos());
    }
}
