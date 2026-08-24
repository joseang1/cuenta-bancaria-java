package com.josedev.bankaccount;

public class SavingsAccount extends Account {
    private boolean active;
    
    public SavingsAccount(float balance, float annualInterestRate) {
        super(balance, annualInterestRate);
        this.active = balance >= 10000;
    }

    @Override
    public void deposit(float amount) {
        if (!this.active) {
            System.out.println("Error: account is inactive");
            return;
        }
        super.deposit(amount);
    }

    @Override
    public void withdraw(float amount) {
        if (!this.active) {
            System.out.println("Error: account is inactive");
            return;
        }
        super.withdraw(amount);
    }

    @Override
    public void monthlyStatement() {
        if (this.numWithdrawals > 4) {
            int extraWithdrawals = this.numWithdrawals - 4;
            this.monthlyFee += extraWithdrawals * 1000;
        }

        super.monthlyStatement();

        this.active = this.balance >= 10000;
    }

    public String print() {
        int totalTransactions = this.numDeposits + this.numWithdrawals;
        return
        "Balance: " + this.balance +
        ", Monthly Fee: " + this.monthlyFee +
        ", Total Transactions: " + totalTransactions;
    }

    public boolean isActive() {
        return this.active;
    }
}
