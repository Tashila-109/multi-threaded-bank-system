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

    // Apply annual charges
    public synchronized void applyAnnualCharges(double charges) {
        if (balance > charges) {
            balance -= charges;
            System.out.println("Annual charges of " + charges + " applied to account " + accountNumber);
        } else {
            System.out.println("Insufficient balance to apply annual charges for account " + accountNumber);
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


}

public class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String accountNumber, AccountHolder accountHolder, double balance, double interestRate) {
        super(accountNumber, accountHolder, balance, AccountType.SAVINGS);
        this.interestRate = interestRate;
    }

    @Override
    public void applyInterest() {
        // Implementation specific to savings account
        double interest = balance * interestRate / 100;
        balance += interest;
        System.out.println("Interest of " + interest + " applied to account " + accountNumber);
    }

    // Savings account specific methods
}

public class CurrentAccount extends BankAccount {
    // Specific attributes for current account if any
    private double overdraftLimit;
    private double overdraftInterestRate; // Interest rate for the used overdraft

    public CurrentAccount(String accountNumber, AccountHolder accountHolder, double balance, double overdraftLimit) {
        super(accountNumber, accountHolder, balance, AccountType.CURRENT);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public synchronized void withdraw(double amount) {
        if (balance + overdraftLimit >= amount) {
            balance -= amount;
            System.out.println(amount + " withdrawn from " + accountNumber + ". Remaining balance: " + balance);
        } else {
            System.out.println("Withdrawal amount exceeds overdraft limit for account " + accountNumber);
        }
    }

    @Override
    public void applyInterest() {
        // Current accounts typically do not have interest
        if (balance < 0) {
            // Apply interest only on the overdrafted amount
            double overdraftUsed = Math.abs(balance);
            double interest = overdraftUsed * overdraftInterestRate / 100;
            balance -= interest; // Deducting interest from balance as it's a charge
            System.out.println("Overdraft interest of " + interest + " applied to current account " + accountNumber);
        }

    }


    public double getOverdraftInterestRate() {
        return overdraftInterestRate;
    }

    // Current account specific methods
    public double getOverdraftLimit() {
        return overdraftLimit;

    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }
}

public class FixedDepositAccount extends BankAccount {
    private double interestRate;
    private int termLength; // The term length in months

    public FixedDepositAccount(String accountNumber, AccountHolder accountHolder, double balance, double interestRate, int termLength) {
        super(accountNumber, accountHolder, balance, AccountType.FIXED_DEPOSIT);
        this.interestRate = interestRate;
        this.termLength = termLength;
    }

    @Override
    public void applyInterest() {
        // Implementation specific to fixed deposit account
        double interest = balance * interestRate / 100;
        balance += interest;
        System.out.println("Interest of " + interest + " applied to account " + accountNumber);
    }


    public double getInterestRate() {
        return interestRate;
    }

    // Fixed deposit account specific methods
}

