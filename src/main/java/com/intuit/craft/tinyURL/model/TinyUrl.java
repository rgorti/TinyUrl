package com.intuit.craft.tinyURL.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TinyUrl {

    @Getter @Setter private String sourceUrl;
    @Getter @Setter private String shortUrl;
    @Getter @Setter private String customUrl;

}
