public class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public BankAccount(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
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

    public synchronized double checkBalance() {
        return balance;
    }

    // Getters and setters for accountNumber and accountHolderName
}
