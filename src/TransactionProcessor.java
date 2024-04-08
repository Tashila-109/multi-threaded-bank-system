public class TransactionProcessor implements Runnable {
    private BankAccount account;
    private double amount;
    private boolean isDeposit; // true for deposit, false for withdrawal

    public TransactionProcessor(BankAccount account, double amount, boolean isDeposit) {
        this.account = account;
        this.amount = amount;
        this.isDeposit = isDeposit;
    }

    @Override
    public void run() {
        if (isDeposit) {
            try {
                account.deposit(amount);
            } catch (IllegalArgumentException e) {
               System.out.println("There was an Illegal Argument present while processing the deposit.");
            }
        } else {
            try {
                account.withdraw(amount);
            } catch (IllegalArgumentException e) {
                System.out.println("There was an Illegal Argument present while processing the withdrawal.");
            }
        }
    }
}
