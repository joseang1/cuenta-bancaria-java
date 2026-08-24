package com.josedev.bankaccount;

public class Account {
    protected float balance;
    protected int numDeposits;
    protected int numWithdrawals;
    protected float annualInterestRate;
    protected float monthlyFee;

    public Account(float balance, float annualInterestRate) {
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
        this.numDeposits = 0;
        this.numWithdrawals = 0;
        this.monthlyFee = 0;
    }

    public void deposit(float amount) {
        this.balance += amount;
        this.numDeposits++;
    }

    public void withdraw(float amount) {
        if (amount > this.balance) {
            System.out.println("Error: withdrawal amount exceeds balance");
            return;
        }
        this.balance -= amount;
        this.numWithdrawals++;
    }

    public float calculateMonthlyInterest() {
        float monthlyRate = this.annualInterestRate / 12 / 100;
        float interest = this.balance * monthlyRate;
        this.balance += interest;
        return interest;
    }

    public void monthlyStatement() {
        this.balance -= this.monthlyFee;
        calculateMonthlyInterest();
    }

    public String print() {
        return
            "Balance: " + this.balance +
            ", Deposits: " + this.numDeposits +
            ", Withdrawals: " + this.numWithdrawals +
            ", Annual interest Rate: " + this.annualInterestRate +
            ", Monthly Fee: " + this.monthlyFee;
    }
}
