package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class TrelloMapper {
    public List<TrelloList> mapToList(final List<TrelloListDto> trelloListDto) {
        return trelloListDto.stream()
                .map(trelloList -> new TrelloList(trelloList.getId(), trelloList.getName(), trelloList.isClosed()))
                .collect(toList());
    }

    public List<TrelloListDto> mapToListDto(final List<TrelloList> trelloList) {
        return trelloList.stream()
                .map(trelloList1 -> new TrelloListDto(trelloList1.getId(), trelloList1.getName(), trelloList1.isClosed()))
                .collect(toList());
    }

    public List<TrelloBoard> mapToBoard(final List<TrelloBoardDto> trelloBoardDto) {
        return trelloBoardDto.stream()
                .map(trelloBoard -> new TrelloBoard(trelloBoard.getId(), trelloBoard.getName(), mapToList(trelloBoard.getLists())))
                .collect(toList());
    }

    public List<TrelloBoardDto> mapToBoardDto(final List<TrelloBoard> trelloBoards) {
        return trelloBoards.stream()
                .map(trelloBoard -> new TrelloBoardDto(trelloBoard.getId(), trelloBoard.getName(), mapToListDto(trelloBoard.getLists())))
                .collect(toList());
    }
    public TrelloCardDto maptoCardDto(final TrelloCard trelloCard){
        return new TrelloCardDto(trelloCard.getName(),trelloCard.getDescription(),trelloCard.getPos(),trelloCard.getListId());
    }
    public TrelloCard mapToCard(final TrelloCardDto trelloCardDto) {
        return new TrelloCard(trelloCardDto.getName(),trelloCardDto.getDescription(),trelloCardDto.getPos(),trelloCardDto.getListId());
    }
}
