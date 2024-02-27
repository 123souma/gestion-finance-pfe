package com.example.finance.Service;

import com.example.finance.Repository.CreditRepository;
import com.example.finance.entites.Credit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Creditservice {

    private final CreditRepository creditRepository;

    @Autowired
    public Creditservice(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    public Credit addCredit(Credit credit) {
        return creditRepository.save(credit);
    }
}
