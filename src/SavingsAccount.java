import Enums.AccountType;

public class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String accountNumber, AccountHolder accountHolder, double balance, double interestRate) {
        super(accountNumber, accountHolder, AccountType.SAVINGS, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void applyInterest() {
        // Implementation specific to savings account
        double interest = balance * interestRate / 100;
        balance += interest;
        System.out.println("Interest of " + interest + " applied to account " + accountNumber);
    }

    @Override
    public void applyAnnualCharges() {
        // Implement specific logic for savings account
        double charges = 10; // Example fixed charge
        if (balance >= charges) {
            balance -= charges;
            System.out.println("Annual charges of " + charges + " applied to savings account " + accountNumber);
        } else {
            System.out.println("Not enough balance to apply annual charges on savings account " + accountNumber);
        }
    }

    // Savings account specific methods
}
