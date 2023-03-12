package com.intuit.craft.tinyURL.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "uri_store")
public class TinyUrlEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "short_url", nullable=false)
    private String shortUrl;

    @Column(name = "source_url", nullable=false)
    private String sourceUrl;

    @Column(name = "custom_url")
    private String customUrl;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "create_date")
    private Date creationDate;

    @Column(name = "expiration_date")
    private Date expirationDate;


}
