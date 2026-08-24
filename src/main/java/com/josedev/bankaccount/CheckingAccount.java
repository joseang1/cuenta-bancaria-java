package com.josedev.bankaccount;

public class CheckingAccount extends Account {
    private float overdraft;

    public CheckingAccount(float balance, float annualInterestRate) {
        super(balance, annualInterestRate);
        this.overdraft = 0;
    }

    @Override
    public void withdraw(float amount) {
        if (amount > this.balance) {
            this.overdraft += (amount - this.balance);
            this.balance = 0;
        } else {
            this.balance -= amount;
        }
        this.numWithdrawals++;
    }

    @Override
    public void deposit(float amount) {
        if (this.overdraft > 0) {
            if (amount >= this.overdraft) {
                float remainder = amount - this.overdraft;
                this.overdraft = 0;
                super.deposit(remainder);
            } else {
                this.overdraft -= amount;
                this.numDeposits++;
            }
        } else {
            super.deposit(amount);
        }
    }

    @Override
    public void monthlyStatement() {
        super.monthlyStatement();
    }

    public String print () {
        int totalTransactions = this.numDeposits + this.numWithdrawals;
        return
            "Balance: " + this.balance +
            ", Monthly Fee: " + this.monthlyFee +
            ", Total Transactions: " + totalTransactions +
            ", Overdraft: " + this.overdraft;
    }

    public float getOverdraft() {
        return this.overdraft;
    }
}
