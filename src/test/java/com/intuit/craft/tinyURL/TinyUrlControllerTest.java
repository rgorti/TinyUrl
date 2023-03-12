package com.intuit.craft.tinyURL;

import com.intuit.craft.tinyURL.controller.TinyUrlController;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.intuit.craft.tinyURL.model.TinyUrl;
import com.intuit.craft.tinyURL.service.TinyUrlService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import static org.mockito.Mockito.verify;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.xml.bind.JAXBException;


@RunWith(MockitoJUnitRunner.class)
public class TinyUrlControllerTest {

    @InjectMocks
    TinyUrlController tinyUrlController;

    @Mock
    TinyUrlService tinyUrlService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreate() throws JAXBException {

        TinyUrl tinyurl = new TinyUrl();
        tinyurl.setSourceUrl("https://www.google.com");
        tinyurl.setCustomUrl("google123");
        tinyurl.setShortUrl("ac6bb66");

        Mockito.when(tinyUrlService.create("https://www.google.com","google123")).thenReturn(tinyurl);
        ResponseEntity<TinyUrl> response = tinyUrlController.create(tinyurl);
        assertTrue(response.getStatusCode().equals(HttpStatus.CREATED));

    }

}

