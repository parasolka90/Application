package com.crud.tasks.config;

import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.config.TrelloConfig;
import com.crud.tasks.trello.factory.FactoryUrl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(MockitoJUnitRunner.class)
public class FactoryUrlTestSuite {

    @InjectMocks
    private FactoryUrl factoryUrl;

    @Mock
    private TrelloConfig trelloConfig;

    @Test
    public void testCreateBoardUrl() throws URISyntaxException {
        //Given
        String endpoint = "http://test1.com";
        String key = "test";
        String token = "test";

        Mockito.when(trelloConfig.getTrelloApiEndpoint()).thenReturn(endpoint);
        Mockito.when(trelloConfig.getTrelloAppKey()).thenReturn(key);
        Mockito.when(trelloConfig.getTrelloAppToken()).thenReturn(token);

        URI resultUrl = factoryUrl.createBoardUrl();
        Assert.assertEquals(new URI("http://test1.com/members/parasolka1/boards?key=test&token=test&fields=name,id&lists=all"),
                resultUrl);
    }

    @Test
    public void testCreateCardUrl() throws URISyntaxException {
        //Given
        String endpoint = "http://test2.com";
        String key = "test2";
        String token = "test2";
        TrelloCardDto trelloCardDto = new TrelloCardDto("test name", "test description", "test pos", "list id");

        Mockito.when(trelloConfig.getTrelloApiEndpoint()).thenReturn(endpoint);
        Mockito.when(trelloConfig.getTrelloAppKey()).thenReturn(key);
        Mockito.when(trelloConfig.getTrelloAppToken()).thenReturn(token);

        URI resultUrl = factoryUrl.createCardUrl(trelloCardDto);
        Assert.assertEquals(new URI("http://test2.com/cards?key=test2&token=test2&name=test%20name&desc=test%20description&pos=test%20pos&idList=list%20id"),
                resultUrl);
    }
}