import Enums.AccountType;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class BankAccount {
    // Define the locks for thread safety
    private final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    protected final Lock readLock = reentrantReadWriteLock.readLock();
    protected final Lock writeLock = reentrantReadWriteLock.writeLock();
    protected String accountNumber;

    protected AccountHolder accountHolder;
    protected AccountType accountType; // Enum for account type
    protected double balance;

    public BankAccount(String accountNumber, AccountHolder accountHolder, AccountType accountType, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.accountType = accountType;
        this.balance = balance;
    }

    public void deposit(double amount) {
        writeLock.lock();
        try {
            balance += amount;
            System.out.println(amount + " deposited. New balance is " + balance);
        } finally {
            writeLock.unlock();
        }
    }

    public void withdraw(double amount) {
        writeLock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
                System.out.println(amount + " withdrawn. Remaining balance is " + balance);
            } else {
                System.out.println("Insufficient balance for withdrawal");
            }
        } finally {
            writeLock.unlock();
        }
    }

    public double getBalance() {
        readLock.lock();
        try {
            return balance;
        } finally {
            readLock.unlock();
        }
    }

    public abstract void applyInterest();

    // Define an abstract method for annual charges
    public abstract void applyAnnualCharges();

    // Getters for accountNumber and accountHolder
    public String getAccountNumber() {
        return accountNumber;
    }

    public AccountHolder getAccountHolder() {
        return accountHolder;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}