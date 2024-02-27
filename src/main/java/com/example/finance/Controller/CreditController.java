package com.example.finance.Controller;
import com.example.finance.Service.Creditservice;
import com.example.finance.entites.Credit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("")
public class CreditController {

    private final Creditservice creditService;

    @Autowired
    public CreditController(Creditservice creditService) {
        this.creditService = creditService;
    }

    @PostMapping("/credits")
    public ResponseEntity<Credit> addCredit(@RequestBody Credit credit) {
        Credit savedCredit = creditService.addCredit(credit);
        return new ResponseEntity<>(savedCredit, HttpStatus.CREATED);
    }
}
