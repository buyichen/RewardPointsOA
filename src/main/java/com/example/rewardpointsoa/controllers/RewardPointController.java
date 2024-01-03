package com.example.rewardpointsoa.controllers;

import com.example.rewardpointsoa.entities.Transaction;
import com.example.rewardpointsoa.services.RewardPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
public class RewardPointController {

    @Autowired
    private RewardPointsService rewardPointsService;

    @PostMapping
    public Transaction saveTransaction(@RequestBody Transaction transaction) {
        return rewardPointsService.saveTransaction(transaction);
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
