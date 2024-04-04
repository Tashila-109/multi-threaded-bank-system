import Enums.AccountType;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, BankAccount> accounts = new HashMap<>();

    public void addAccount(String accountNumber, String holderName, String holderId, String holderPhoneNumber, String holderAddress, String holderPersonalid, double initialBalance, AccountType accountType) {
        AccountHolder accountHolder = new AccountHolder(holderName, holderId, holderPhoneNumber, holderAddress, holderPersonalid);
        BankAccount account = new BankAccount(accountNumber, accountHolder, accountType, initialBalance);
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

    public BankAccount getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }
}
