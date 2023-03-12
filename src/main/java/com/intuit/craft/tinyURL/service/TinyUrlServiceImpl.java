package com.intuit.craft.tinyURL.service;

import com.intuit.craft.tinyURL.entity.TinyUrlEntity;
import com.intuit.craft.tinyURL.exception.*;
import com.intuit.craft.tinyURL.model.TinyUrl;
import com.intuit.craft.tinyURL.repository.TinyUrlRepository;
import com.intuit.craft.tinyURL.util.URLGenerator;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

@Service
public class TinyUrlServiceImpl implements TinyUrlService{

    private static final Logger LOGGER = LoggerFactory.getLogger(TinyUrlServiceImpl.class);

    @Autowired
    private TinyUrlRepository tinyUrlRepository;

    @Autowired
    private URLGenerator urlGenerator;


    @Getter @Setter
    private TinyUrlEntity entity;

    public String getSourceUrl(String shortUrl) {

        String sourceUrl = null;

        if ((shortUrl.length() < 7) || (shortUrl.length() >7)) {
            LOGGER.error("The given Url is invalid");
            throw new InvalidUrlException("The given Shortner is invalid");
        }
        if (!existsTiny(shortUrl)) {
            LOGGER.error("The given shortURL String does not exist in DB");
            throw new NoSuchUrlException(" The given shortURL String does not exist in DB");
        }
        try {
            entity = tinyUrlRepository.findByShortUrl(shortUrl);
            if (entity != null) {
                LOGGER.info("Getting source url from the server..." + entity.getSourceUrl());
                sourceUrl = entity.getSourceUrl();
            }
        } catch (DataAccessException dae)  {
            LOGGER.error("Failed to get data from DB");
            throw new InternalServerException("Failed to get Data due internal server Error");
        }
        return sourceUrl;
    }

    public String getSourceUrl(String shortUrl,String customUrl) {
        String sourceUrl = null;
        if ((shortUrl.length() < 7) || (shortUrl.length() >7)) {
            LOGGER.error("The given Url is invalid");
            throw new InvalidUrlException("The given Shortner is invalid");
        }
        if (!existsTiny(shortUrl)) {
            LOGGER.error("The given shortURL String does not exist in DB");
            throw new NoSuchUrlException(" The given shortURL String does not exist in DB");
        }
        if (!existsCustom(customUrl)) {
            throw new NoSuchUrlException("The given custom URL does not exist");
        }
        try {
            entity = tinyUrlRepository.findByShortUrl(shortUrl);
            if (entity.getCustomUrl().equalsIgnoreCase(customUrl)) {
                sourceUrl = entity.getSourceUrl();
                LOGGER.info("Getting source url from the server..." + sourceUrl);
            } else {
                throw new NoSuchUrlException("The Custom URL given is Invalid for the short URL");
            }

        } catch (DataAccessException dataex) {
            LOGGER.error("Failed to Get data from DB");
            throw new InternalServerException("Failed to Get URL due to internal Server error");
        }
        return sourceUrl;
    }

    public TinyUrl create(String sourceUrl, String customUrl){
        TinyUrl response = new TinyUrl();
        // validate sourceurl
        if (!isValid(sourceUrl)) {
            LOGGER.error("The given URL is not valid");
            throw new InvalidUrlException("The given source URL is not Valid. Please provide valid sourceurl");
        }
        if (customUrl != null) {
                if ((customUrl.length() < 5) || (customUrl.length() > 12))
                    throw new InvalidUrlException("The given custom URL is too short or exceeds limit. Min number of characters for custom url is 5 char and Max to 12");
                if (existsCustom(customUrl)) {
                    throw new UrlAlreadyExistsException("The given Custom URL already exists in the Database. Choose different custom name");
                }
        }
        try {
            String shortUrl = urlGenerator.generateShortUrl(sourceUrl);
            TinyUrlEntity request = buildRequest(sourceUrl, customUrl, shortUrl);
            TinyUrlEntity entity = tinyUrlRepository.save(request);
            response.setShortUrl(entity.getShortUrl());
            response.setCustomUrl(entity.getCustomUrl());
            LOGGER.info("Created TinyUrl......." + shortUrl);
        } catch (DataAccessException dataex){
            LOGGER.error("Error while saving data to DB");
            throw new InternalServerException("Failed to create URL due to internal Server error");
        }
        catch (Exception e){
            LOGGER.error("Internal Server Error " );
            throw new InternalServerException("Internal Server Exception");
        }

        return response;
    }

    private TinyUrlEntity buildRequest(String sourceUrl,String customUrl, String shortUrl) {
        TinyUrlEntity request = new TinyUrlEntity();
        request.setSourceUrl(sourceUrl);
        request.setShortUrl(shortUrl);
        request.setCustomUrl(customUrl);

        // Set creation Date and Expiry Date
        LocalDate localDate = LocalDate.now();
        LocalDate expiryDate = localDate.plusYears(3);
        java.sql.Date dbDate = java.sql.Date.valueOf(localDate);
        java.sql.Date dbExpiryDate = java.sql.Date.valueOf(expiryDate);
        request.setCreationDate(dbDate);
        request.setExpirationDate(dbExpiryDate);
        return request;
    }

    private boolean isValid(String url) {
        return UrlValidator.getInstance().isValid(url);
    }

    private boolean existsCustom(String customUrl) {
       return tinyUrlRepository.existsTinyUrlEntitiesByCustomUrl(customUrl);

    }

    private boolean existsTiny(String shortUrl){
        return tinyUrlRepository.existsTinyUrlEntityByShortUrl(shortUrl);
    }



}
