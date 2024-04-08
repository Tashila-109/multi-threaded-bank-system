import Enums.AccountType;

public class CurrentAccount extends BankAccount {
    // Specific attributes for current account if any
    private double overdraftLimit;
    private double overdraftInterestRate; // Interest rate for the used overdraft

    public CurrentAccount(String accountNumber, AccountHolder accountHolder, double balance, double overdraftLimit) {
        super(accountNumber, accountHolder, AccountType.CURRENT, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        writeLock.lock();
        try {
            if (amount <= 0) {
                throw new IllegalArgumentException("Withdrawal amount must be positive.");
            }
            if (balance + overdraftLimit >= amount) {
                balance -= amount;
                System.out.println(amount + " withdrawn from " + accountNumber + ". Remaining balance: " + balance);
            } else {
                System.out.println("Withdrawal amount exceeds overdraft limit for account " + accountNumber);
            }
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void applyInterest() {
        writeLock.lock();
        try {
            // Current accounts typically do not have interest
            if (balance < 0) {
                // Apply interest only on the overdrafted amount
                double overdraftUsed = Math.abs(balance);
                double interest = overdraftUsed * overdraftInterestRate / 100;
                balance -= interest; // Deducting interest from balance as it's a charge
                System.out.println("Overdraft interest of " + interest + " applied to current account " + accountNumber);
            }
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void applyAnnualCharges() {
        writeLock.lock();
        try {
            // Implement specific logic for current account
            double charges = 50; // Example fixed charge for current accounts
            if (balance >= charges) {
                balance -= charges;
                System.out.println("Annual charges of " + charges + " applied to current account " + accountNumber);
            } else {
                System.out.println("Not enough balance to apply annual charges on current account " + accountNumber);
            }
        } finally {
            writeLock.unlock();
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
