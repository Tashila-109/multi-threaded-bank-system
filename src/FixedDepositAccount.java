import Enums.AccountType;

public class FixedDepositAccount extends BankAccount {
    private final double interestRate;
    private int termLength; // The term length in months

    public FixedDepositAccount(String accountNumber, AccountHolder accountHolder, double balance, double interestRate, int termLength) {
        super(accountNumber, accountHolder, AccountType.FIXED_DEPOSIT, balance);
        this.interestRate = interestRate;
        this.termLength = termLength;
    }

    @Override
    public void applyInterest() {
        writeLock.lock();
        try {
            // Implementation specific to fixed deposit account
            double interest = balance * interestRate / 100;
            balance += interest;
            System.out.println("Interest of " + interest + " applied to account " + accountNumber);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void applyAnnualCharges() {
        // Fixed deposit accounts may have different charge logic or no annual charges
        System.out.println("No annual charges for fixed deposit account " + accountNumber);
    }


    public double getInterestRate() {
        return interestRate;
    }

    // Fixed deposit account specific methods
}
