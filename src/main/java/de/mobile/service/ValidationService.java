package de.mobile.service;

import de.mobile.model.MobileAd;
import de.mobile.model.MobileCustomer;
import de.mobile.repository.MobileAdRepository;
import de.mobile.repository.MobileCustomerRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidationService {
    private final Logger LOGGER = LoggerFactory.getLogger(ValidationService.class);
    @Autowired
    private MobileAdRepository mobileAdRepository;
    @Autowired
    private MobileCustomerRepository mobileCustomerRepository;

    public MobileAd getValidMobileAd(Long id) {
        Optional<MobileAd> foundMobileAd = mobileAdRepository.findById(id);
        if (foundMobileAd.isPresent()) {
            throw new IllegalArgumentException("Mobile Ad already exists.");
        }
        Optional<MobileCustomer> foundMobileCustomer = mobileCustomerRepository.findById(id);
        if (!foundMobileCustomer.isPresent()) {
            throw new IllegalArgumentException("Mobile Customer does not exist.");
        }
        return foundMobileAd.get();
    }

    public MobileCustomer getValidMobileCustomer(Long id, MobileCustomer mobileCustomer) {
        Optional<MobileCustomer> foundCustomer = mobileCustomerRepository.findById(id);
        if (foundCustomer.isPresent() ||
                mobileCustomerRepository.findMobileCustomerByEmail(mobileCustomer.getEmail()) != null) {
            LOGGER.error("Mobile Customer already exists.");
            throw new IllegalArgumentException("Mobile Customer already exists.");
        }
        if (!isValidEmailAddress(mobileCustomer.getEmail())) {
            LOGGER.error("Customer E-Mail not valid.");
            throw new IllegalArgumentException("Customer E-Mail not valid.");
        }
        return mobileCustomer;
    }

    public boolean isValidEmailAddress(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    public MobileCustomer checkCustomerExists(Long id) {
        Optional<MobileCustomer> foundCustomer = mobileCustomerRepository.findById(id);
        if (!foundCustomer.isPresent()) {
            throw new IllegalArgumentException("Mobile Customer does not exist.");
        }
        return foundCustomer.get();
    }

    public MobileAd checkMobileAdExists(Long id) {
        Optional<MobileAd> foundMobileAd = mobileAdRepository.findById(id);
        if (!foundMobileAd.isPresent()) {
            throw new IllegalArgumentException("Mobile Ad already exists.");
        }
        return foundMobileAd.get();
    }
}
