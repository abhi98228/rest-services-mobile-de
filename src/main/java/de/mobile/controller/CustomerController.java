package de.mobile.controller;

import de.mobile.model.MobileCustomer;
import de.mobile.repository.MobileCustomerRepository;
import de.mobile.service.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private MobileCustomerRepository mobileCustomerRepository;
    @Autowired
    private ValidationService validationService;

    @PostMapping("/add/{id}")
    public ResponseEntity addCustomer(
            @PathVariable
            Long id,
            @RequestBody
            MobileCustomer mobileCustomer) {
        LOGGER.trace("Inside addCustomer");
        MobileCustomer foundCustomer = validationService.getValidMobileCustomer(id, mobileCustomer);
        LOGGER.debug("Customer " + mobileCustomer.getFirstName() + " found.");
        mobileCustomer.setId(id);
        mobileCustomerRepository.save(mobileCustomer);
        return ResponseEntity.ok("User Added");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getMobileCustomer(
            @PathVariable
            Long id) {
        return ResponseEntity.ok(validationService.checkCustomerExists(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMobileCustomer(
            @PathVariable
            Long id) {
        mobileCustomerRepository.delete(validationService.checkCustomerExists(id));
        return ResponseEntity.ok("Mobile Customer Deleted");
    }
}
