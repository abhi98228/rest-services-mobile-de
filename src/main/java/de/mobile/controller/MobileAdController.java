package de.mobile.controller;


import de.mobile.model.MobileAd;
import de.mobile.repository.MobileAdRepository;
import de.mobile.repository.MobileCustomerRepository;
import de.mobile.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/mobilead")
public class MobileAdController {
    @Autowired
    private MobileAdRepository mobileAdRepository;
    @Autowired
    private MobileCustomerRepository mobileCustomerRepository;
    @Autowired
    private ValidationService validationService;

    @PostMapping("/add/{id}")
    public ResponseEntity addMobileAd(
            @PathVariable
            Long id,
            @RequestBody
            MobileAd mobileAd) {
        MobileAd validMobileAd = validationService.getValidMobileAd(id);
        validMobileAd.setCustomerId(id);
        mobileAdRepository.save(validMobileAd);
        return ResponseEntity.ok("Mobile Ad Added");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getMobileAd(
            @PathVariable
            Long id) {
        return ResponseEntity.ok(validationService.checkMobileAdExists(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMobileAd(
            @PathVariable
            Long id) {
        mobileAdRepository.delete(validationService.checkMobileAdExists(id));
        return ResponseEntity.ok("Mobile Customer Deleted");
    }
}
