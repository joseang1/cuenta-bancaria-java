package com.josedev.bankaccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountTest {

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account(1000f, 12f);
    }

    @Test
    void constructorInitializesFieldsCorrectly() {
        assertEquals(1000f, account.balance);
        assertEquals(12f, account.annualInterestRate);
        assertEquals(0, account.numDeposits);
        assertEquals(0, account.numWithdrawals);
        assertEquals(0f, account.monthlyFee);
    }

    @Test
    void depositIncreasesBalanceAndCount() {
        account.deposit(500f);

        assertEquals(1500f, account.balance);
        assertEquals(1, account.numDeposits);
    }

    @Test
    void withdrawDecreasesBalanceAndCountWhenSufficientFunds() {
        account.withdraw(400f);

        assertEquals(600f, account.balance);
        assertEquals(1, account.numWithdrawals);
    }

    @Test
    void withdrawDoesNothingWhenAmountExceedsBalance() {
        account.withdraw(4000f);

        assertEquals(1000f, account.balance);
        assertEquals(0, account.numWithdrawals);
    }

    @Test
    void calculateMonthlyInterestUpdatesBalance() {
        float interest = account.calculateMonthlyInterest();
        float expectedInterest = 1000f * (12f / 12 / 100);

        assertEquals(expectedInterest, interest, 0.001);
        assertEquals(1000f + expectedInterest, account.balance, 0.001);
    }

    @Test
    void monthlyStatementSubtractsFeeAndAddsInterest() {
        account.monthlyFee = 50f;
        account.monthlyStatement();

        float afterFee = 950f;
        float expectedInterest = afterFee * (12f / 12 / 100);

        assertEquals(afterFee + expectedInterest, account.balance, 0.001);
    }

    @Test
    void printContainsExpectedValues() {
        String result = account.print();

        assertTrue(result.contains("1000.0"));
        assertTrue(result.contains("12.0"));
    }
}
