public class BalanceReporter implements Runnable {
    private BankAccount account;

    public BalanceReporter(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        System.out.println(STR."The balance for account \{account.getAccountNumber()} is: \{account.getBalance()}");
    }
}
