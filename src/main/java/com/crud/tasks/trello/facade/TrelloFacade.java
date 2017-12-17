package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.client.CreatedTrelloDto;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


import static java.util.stream.Collectors.toList;

@Component
public class TrelloFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloFacade.class);
    @Autowired
    private TrelloService trelloService;
    @Autowired
    private TrelloMapper trelloMapper;
    @Autowired
    TrelloValidator trelloValidator;

    public List<TrelloBoardDto> fetchTrelloBoards(){

        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoard(trelloService.fetchTrelloBoards());

        LOGGER.info("Starting filtering Boards...");
        List<TrelloBoard>filteredBoards = trelloBoards.stream()
//                .filter(trelloBoard -> trelloBoard.getName().equalsIgnoreCase("test"))
                .collect(toList());
        LOGGER.info("Boards has been filtered. Current last size: "+filteredBoards.size());
        return trelloMapper.mapToBoardDto(filteredBoards);
    }
    public CreatedTrelloDto createTrello(final TrelloCardDto trelloCardDto){
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        trelloValidator.validateCard(trelloCard);
        return trelloService.createdTrelloCard(trelloMapper.maptoCardDto(trelloCard));
    }
}
