import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, BankAccount> accounts = new HashMap<>();

    public void addAccount(String accountNumber, String accountHolderName, double initialBalance) {
        BankAccount account = new BankAccount(accountNumber, accountHolderName, initialBalance);
        accounts.put(accountNumber, account);
    }

    public BankAccount getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }
}
