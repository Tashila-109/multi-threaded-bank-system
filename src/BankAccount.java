import Enums.AccountType;

public abstract class BankAccount {
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

    public synchronized void deposit(double amount) {
        balance += amount;
        System.out.println(amount + " deposited. New balance is " + balance);
    }

    public synchronized void withdraw(double amount)  {
        if (balance >= amount) {
            balance -= amount;
            System.out.println(amount + " withdrawn. Remaining balance is " + balance);
        } else {
            System.out.println("Insufficient balance for withdrawal");
        }
    }

    public abstract void applyInterest();

    // Define an abstract method for annual charges
    public abstract void applyAnnualCharges();

    public synchronized double getBalance() {
        return balance;
    }

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

