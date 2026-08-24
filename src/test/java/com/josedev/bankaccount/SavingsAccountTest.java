package com.josedev.bankaccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SavingsAccountTest {
    
    private SavingsAccount account;

    @BeforeEach
    void setUp() {
        account = new SavingsAccount(15000f, 12f);
    }

    @Test
    void accountIsActiveWhenInitialBalanceBelowMinimum() {
        SavingsAccount lowBalanceAccount = new SavingsAccount(5000f, 12f);
        
        assertFalse(lowBalanceAccount.isActive());
    }

    @Test
    void accountIsInactiveWhenInitialBalanceMeetsMinimum() {
        assertTrue(account.isActive());
    }

    @Test
    void depositWorksWhenAccountIsActive() {
        account.deposit(1000f);

        assertEquals(16000f, account.balance);
        assertEquals(1, account.numDeposits);
    }

    @Test
    void depositDoesNothingWhenAccountIsInactive() {
        SavingsAccount inactiveAccount = new SavingsAccount(5000f, 12f);

        inactiveAccount.deposit(1000f);

        assertEquals(5000f, inactiveAccount.balance);
        assertEquals(0, inactiveAccount.numDeposits);
    }

    @Test
    void withdrawWorksWhenAccountIsActive() {
        account.withdraw(1000f);

        assertEquals(14000f, account.balance);
        assertEquals(1, account.numWithdrawals);
    }

    @Test
    void withdrawDoesNothingWhenAccountIsInactive() {
        SavingsAccount innactiveAccount = new SavingsAccount(5000f, 12f);

        innactiveAccount.withdraw(500f);

        assertEquals(5000f, innactiveAccount.balance);
        assertEquals(0, innactiveAccount.numWithdrawals);
    }

    @Test
    void monthlyStatementChargesExtraFeeAfterFourWithdrawals() {
        account.withdraw(100f);
        account.withdraw(100f);
        account.withdraw(100f);
        account.withdraw(100f);
        account.withdraw(100f);

        account.monthlyStatement();

        assertEquals(1000f, account.monthlyFee);
    }

    @Test
    void monthlyStatementDoesNotChargeExtraFeeWithFourOrFewerWithdrawals() {
        account.withdraw(100f);
        account.withdraw(100f);

        account.monthlyStatement();

        assertEquals(0f, account.monthlyFee);
    }

    @Test
    void monthlyStatementDeactivatesAccountWhenBalanceDepositDropsBelowMinimum() {
        SavingsAccount edgeAccount = new SavingsAccount(10500f, 0f);
        edgeAccount.monthlyFee = 1000f;

        edgeAccount.monthlyStatement();

        assertFalse(edgeAccount.isActive());
    }

    @Test
    void printReturnsExpectedFormat() {
        String result = account.print();

        assertTrue(result.contains("15000.0"));
        assertTrue(result.contains("Total Transactions"));
    }
}
