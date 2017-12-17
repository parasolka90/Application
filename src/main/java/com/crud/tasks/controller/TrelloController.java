package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.client.CreatedTrelloDto;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    TrelloFacade trelloFacade;

   @GetMapping(value = "/boards")
   public List<TrelloBoardDto> getTrelloBoards(){ return trelloFacade.fetchTrelloBoards();}

  @PostMapping(value = "/cards")
    public CreatedTrelloDto createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto){
       return trelloFacade.createTrello(trelloCardDto);
   }
}