package com.intuit.craft.tinyURL.service;

import com.intuit.craft.tinyURL.model.TinyUrl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface TinyUrlService {

    String getSourceUrl(String shortUrl);

    String getSourceUrl(String shortUrl, String customUrl);

    TinyUrl create(String sourceUrl, String customUrl);

}



