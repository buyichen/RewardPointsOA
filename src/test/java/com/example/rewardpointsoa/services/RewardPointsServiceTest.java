package com.example.rewardpointsoa.services;

import com.example.rewardpointsoa.entities.Transaction;
import com.example.rewardpointsoa.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RewardPointsServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private RewardPointsService rewardPointsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCalculateRewardPointsFor120() {
        double purchaseAmount = 120.0;
        int expectedPoints = 90; // Expected points for the given purchase amount
        int actualPoints = rewardPointsService.calculateRewardPoints(purchaseAmount);
        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    void testCalculateRewardPointsFor51() {
        double purchaseAmount = 51.0;
        int expectedPoints = 1;
        int actualPoints = rewardPointsService.calculateRewardPoints(purchaseAmount);
        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    void testCalculateRewardPointsFor100() {
        double purchaseAmount = 100.0;
        int expectedPoints = 50;
        int actualPoints = rewardPointsService.calculateRewardPoints(purchaseAmount);
        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    void testCalculateRewardPointsFor220() {
        double purchaseAmount = 220.0;
        int expectedPoints = 290; // Expected points for the given purchase amount
        int actualPoints = rewardPointsService.calculateRewardPoints(purchaseAmount);
        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    void testSaveTransaction() {
        Transaction mockTransaction = new Transaction(1L, 120.0, LocalDate.now());
        when(transactionRepository.save(any())).thenReturn(mockTransaction);

        Transaction savedTransaction = rewardPointsService.saveTransaction(mockTransaction);

        verify(transactionRepository, times(1)).save(mockTransaction);
        assertEquals(mockTransaction, savedTransaction);
    }

}
