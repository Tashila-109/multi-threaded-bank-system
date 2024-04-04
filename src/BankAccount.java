import Enums.AccountType;

public class BankAccount {
    private String accountNumber;
    private AccountHolder accountHolder;
    private AccountType accountType; // Enum for account type
    private double balance;

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

    // Getters and setters for accountNumber and accountHolderName
}
