package com.example.rewardpointsoa.controllers;

import com.example.rewardpointsoa.entities.Transaction;
import com.example.rewardpointsoa.services.RewardPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
public class RewardPointController {

    @Autowired
    private RewardPointsService rewardPointsService;

    @PostMapping
    public ResponseEntity<List<Transaction>> saveTransactions(@RequestBody List<Transaction> transactions) {
        return new ResponseEntity<>(rewardPointsService.saveTransactions(transactions), HttpStatus.CREATED);
    }

    @GetMapping("/customer/{customerId}/monthly-reward-points")
    public Map<Month, Integer> getRewardPointsForCustomerPerMonth(
            @PathVariable Long customerId) {
        return rewardPointsService.getRewardPointsForCustomerPerMonth(customerId);
    }

    @GetMapping("/customer/{customerId}/total-reward-points")
    public int getTotalRewardPointsForCustomer(@PathVariable Long customerId) {
        return rewardPointsService.getTotalRewardPointsForCustomer(customerId);
    }
}
