package com.example.rewardpointsoa.services;

import com.example.rewardpointsoa.entities.Transaction;
import com.example.rewardpointsoa.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RewardPointsService {

    @Autowired
    private TransactionRepository transactionRepository;

    public int calculateRewardPoints(double purchaseAmount) {
        if (purchaseAmount > 50 && purchaseAmount <= 100) {
            return (int) Math.round(purchaseAmount - 50);
        } else if (purchaseAmount > 100) {
            return (int) (Math.round(purchaseAmount - 100) * 2 + (100 - 50));
        } else {
            return 0;
        }
    }

    public Transaction saveTransaction(Transaction transaction) {
        // Calculate and set reward points before saving
        int rewardPoints = calculateRewardPoints(transaction.getPurchaseAmount());
        transaction.setRewardPoints(rewardPoints);

        return transactionRepository.save(transaction);
    }

    public Map<Month, Integer> getRewardPointsForCustomerPerMonth(Long customerId) {
        List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);
        return transactions.stream()
                .collect(Collectors.groupingBy(transaction -> transaction.getTransactionDate().getMonth(),
                        Collectors.summingInt(Transaction::getRewardPoints)));
    }

    public int getTotalRewardPointsForCustomer(Long customerId) {
        List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);
        return transactions.stream()
                .collect(Collectors.summingInt(Transaction::getRewardPoints));
    }
}
