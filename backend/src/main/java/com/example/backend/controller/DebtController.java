package com.example.backend.controller;


import com.example.backend.model.Debt;
import com.example.backend.repository.DebtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/debts")
public class DebtController {
    @Autowired
    private DebtRepository debtRepository;

    @GetMapping
    public List<Debt> getAllDebts() {
        return debtRepository.findAll();
    }

    @PostMapping
    public Debt addDebt(@RequestBody Debt debt) {
        return debtRepository.save(debt);
    }

    @GetMapping("/total")
    public double getTotalDebt() {
        return debtRepository.findAll().stream().mapToDouble(Debt::getAmount).sum();
    }

    @GetMapping("/paid")
    public List<Debt> getPaidDebts() {
        return debtRepository.findAll().stream()
                .filter(debt -> debt.getPercentagePaid() == 100)
                .toList();
    }

    @PostMapping("/{id}/pay")
    public Debt payDebt(@PathVariable Long id, @RequestParam double payment, @RequestParam(required = false) String screenshotPath) {
        Debt debt = debtRepository.findById(id).orElseThrow();
        debt.setPercentagePaid(Math.min(debt.getPercentagePaid() + payment / debt.getAmount() * 100, 100));
        if (screenshotPath != null) {
            debt.setScreenshotPath(screenshotPath);
        }
        return debtRepository.save(debt);
    }
}