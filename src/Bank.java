import Enums.AccountType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, BankAccount> accounts = new HashMap<>();

    public void addAccount(String accountNumber, AccountHolder holder, double initialBalance, AccountType accountType, double interestRate, double overdraftLimit) {
        BankAccount account = switch (accountType) {
            case SAVINGS -> new SavingsAccount(accountNumber, holder, initialBalance, interestRate);
            case CURRENT -> new CurrentAccount(accountNumber, holder, initialBalance, overdraftLimit);
            case FIXED_DEPOSIT -> {
                // Assuming fixed deposit accounts have a term length and interest rate
                int termLength = 12; // Example term length in months
                yield new FixedDepositAccount(accountNumber, holder, initialBalance, interestRate, termLength);
            }
            default -> throw new IllegalArgumentException("Invalid account type");
        };
        accounts.put(accountNumber, account);
    }

    public void removeAccount(String accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            accounts.remove(accountNumber);
            System.out.println("Account " + accountNumber + " has been removed.");
        } else {
            System.out.println("Account " + accountNumber + " not found.");
        }
    }

    public void applyInterestToAllAccounts() {
        for (BankAccount account : accounts.values()) {
            account.applyInterest();
            System.out.println("Interest applied to account: " + account.getAccountNumber());
        }
    }

    public void applyAnnualChargesToAllAccounts() {
        System.out.println("Applying annual charges to all accounts...");
        for (BankAccount account : accounts.values()) {
            account.applyAnnualCharges();
        }
    }

    public BankAccount getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public Collection<BankAccount> getAllAccounts() {
        return accounts.values();
    }
}
