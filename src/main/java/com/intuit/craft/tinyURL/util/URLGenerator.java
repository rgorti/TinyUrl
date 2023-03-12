package com.intuit.craft.tinyURL.util;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.intuit.craft.tinyURL.repository.TinyUrlRepository;
import com.intuit.craft.tinyURL.service.TinyUrlServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component
public class URLGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(TinyUrlServiceImpl.class);
    @Autowired
    private TinyUrlRepository repository;
    private static int SHORT_URL_CHAR_SIZE=7;

        // Not used
    public String convert(String longURL) {
        try {
            // Create MD5 Hash
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(longURL.getBytes());
            byte messageDigest[] = md.digest();

                // convert byte to hexstring
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(Integer.toHexString(0xFF & b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public String generateShortUrl(String sourceUrl) {
        String hash = generateHash(sourceUrl);
        int numberOfCharsInHash=hash.length();
        int counter=0;
        while(counter < numberOfCharsInHash-SHORT_URL_CHAR_SIZE){
            if(!isUrlExists(hash.substring(counter, counter+SHORT_URL_CHAR_SIZE))){
                return hash.substring(counter,counter+SHORT_URL_CHAR_SIZE).trim();
            }
            counter++;
        }
        return hash;
    }
    // Generate Hash usign google guava Sha256 algorithm
    private String generateHash(String sourceUrl) {
        HashCode shortUrl = Hashing.sha256().hashString(sourceUrl, Charset.defaultCharset());
        return shortUrl.toString();
    }

    private boolean isUrlExists(String url){
        return repository.existsTinyUrlEntityByShortUrl(url);
    }

}


