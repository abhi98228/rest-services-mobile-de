package de.mobile.repository;

import de.mobile.model.MobileAd;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MobileAdRepository extends MongoRepository<MobileAd, Long> {
}
