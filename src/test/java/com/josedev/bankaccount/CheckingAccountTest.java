package com.josedev.bankaccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckingAccountTest {
    
    private CheckingAccount account;

    @BeforeEach
    void setUp() {
        account = new CheckingAccount(1000f, 6f);
    }
    @Test
    void constructorInitializesOverdraftToZero() {
        assertEquals(0f, account.getOverdraft());
    }

    @Test
    void withdrawWithinBalanceDoesNotCreateOverdraft() {
        account.withdraw(400f);

        assertEquals(600f, account.balance);
        assertEquals(0f, account.getOverdraft());
        assertEquals(1, account.numWithdrawals);
    }

    @Test
    void withdrawExceedingBalanceCreatesOverdraft() {
        account.withdraw(1500f);

        assertEquals(0f, account.balance);
        assertEquals(500f, account.getOverdraft());
        assertEquals(1, account.numWithdrawals);
    }

    @Test
    void depositFullyCoversOverDraft() {
        account.withdraw(1500f);
        account.deposit(800f);

        assertEquals(0f, account.getOverdraft());
        assertEquals(300f, account.balance);
    }

    @Test
    void depositPartiallyCoversOverdraft() {
        account.withdraw(1500f);
        account.deposit(200f);

        assertEquals(300f, account.getOverdraft());
    }

    @Test
    void depositWithNoOverdraftJustIncreasesBalance() {
        account.deposit(500f);

        assertEquals(1500f, account.balance);
        assertEquals(0, account.getOverdraft());
    }

    @Test
    void monthlyStatementAppliesFeeAndInterest() {
        account.monthlyFee = 10f;
        account.monthlyStatement();

        float afterFee = 990f;
        float expectedInterest = afterFee * (6f / 12 / 100);

        assertEquals(afterFee + expectedInterest, account.balance, 0.001);
    }

    @Test
    void printReturnsExpectedFormat() {
        account.withdraw(1500f);
        
        String result = account.print();

        assertTrue(result.contains("Overdraft"));
        assertTrue(result.contains("500.0"));
    }
}
