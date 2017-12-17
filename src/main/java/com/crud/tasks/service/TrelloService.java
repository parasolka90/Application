package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.CreatedTrelloDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.crud.tasks.service.MailCreatorService.INFO_EMAIL;
import static java.util.Optional.ofNullable;

@Service
public class TrelloService {
    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private TrelloClient trelloClient;

    @Autowired
    SimpleEmailService emailService;

    public List<TrelloBoardDto> fetchTrelloBoards(){
        return trelloClient.getTrelloBoards();
    }

    private static final String SUBJECT = "Tasks: New Trello Card";
    public CreatedTrelloDto createdTrelloCard(final TrelloCardDto trelloCardDto){
        CreatedTrelloDto newCard = trelloClient.createNewCard(trelloCardDto);

        ofNullable(newCard).ifPresent(card->emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT,
                "New card: "+card.getName()+" has been created on your Trello account"), INFO_EMAIL));

        return newCard;
    }
}

