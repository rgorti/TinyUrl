package com.intuit.craft.tinyURL.controller;

import com.intuit.craft.tinyURL.model.TinyUrl;
import com.intuit.craft.tinyURL.service.TinyUrlService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController
public class TinyUrlController {

    @Autowired
    private TinyUrlService tinyUrlService;

    private static String SERVER_URL="http://localhost:8080";
    private static String DOMAIN_NAME=SERVER_URL+ "/tinyurl/";

    @RequestMapping(method=RequestMethod.GET,value="/tinyurl/{shortUrl}")
    public ResponseEntity<Void> getSourceUrl(@PathVariable String shortUrl) {
        String sourceUrl = tinyUrlService.getSourceUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(sourceUrl))
                .build();
    }


    @RequestMapping(method=RequestMethod.GET,value="/tinyurl/{shortUrl}/{customUrl}")
    public ResponseEntity<Void> getSourceUrl(@PathVariable("shortUrl") String shortUrl, @PathVariable("customUrl") String customUrl) {
        String sourceUrl = tinyUrlService.getSourceUrl(shortUrl,customUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(sourceUrl))
                .build();
    }

    @RequestMapping(method=RequestMethod.POST,value="/tinyurl/create")
    public ResponseEntity<TinyUrl> create(@RequestBody TinyUrl request){
        TinyUrl response = tinyUrlService.create(request.getSourceUrl(),request.getCustomUrl());
        String uri = DOMAIN_NAME + response.getShortUrl();
        response.setShortUrl(uri);
        if (request.getCustomUrl() !=null) {
            String customUri = uri +"/"  + response.getCustomUrl();
            response.setCustomUrl(customUri);
        }
        return new ResponseEntity<TinyUrl>(response, HttpStatus.CREATED);
    }

}
