package org.spring.ensapay.service;

import org.spring.ensapay.entity.Debt;
import org.spring.ensapay.repository.DebtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DebtServcie {

    @Autowired
    private DebtRepository debtRepository;


}
