public class Client implements Runnable {
    private BankAccount account;
    private double amountToProcess;
    private Bank bank;

    public Client(Bank bank, String accountNumber, double amountToProcess) {
        this.bank = bank;
        this.account = bank.getAccount(accountNumber);
        this.amountToProcess = amountToProcess;
    }

    @Override
    public void run() {
        // Perform some banking operations, e.g., deposit, withdraw, check balance
        // For demonstration, let's do a deposit
        if (account != null) {
            account.deposit(amountToProcess);
        }
    }
}
