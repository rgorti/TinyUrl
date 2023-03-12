package com.intuit.craft.tinyURL.repository;

import com.intuit.craft.tinyURL.entity.TinyUrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TinyUrlRepository extends JpaRepository<TinyUrlEntity, Long> {

    TinyUrlEntity findByShortUrl(String shortUrl);

    boolean existsTinyUrlEntityByShortUrl(String shortUrl);

    boolean existsTinyUrlEntitiesByCustomUrl(String customUrl);

/*
    @Query(value = "select source_url from uri_store where short_url=?1", nativeQuery = true)
    String findSourceUrlBy(String id);

    @Query(value = "select source_url from uri_store where short_url=?1 and custom_url=?2", nativeQuery = true)
    String findSourceUrlBy(String id1, String id2);

 */
}
