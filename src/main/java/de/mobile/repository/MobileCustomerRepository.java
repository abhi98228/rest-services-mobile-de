package de.mobile.repository;

import de.mobile.model.MobileCustomer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MobileCustomerRepository extends MongoRepository<MobileCustomer, Long> {
    @Query(fields = "{email : 1}")
    MobileCustomer findMobileCustomerByEmail(String email);
}
