import Enums.AccountType;

public class BankSystemSimulation {
    public static void main(String[] args) {
        Bank bank = new Bank();

        // Create account holders and accounts
        AccountHolder holder1 = new AccountHolder("John Doe", "ID123");
        AccountHolder holder2 = new AccountHolder("Jane Doe", "ID456");

        bank.addAccount("123", holder1, 1000.00, AccountType.REGULAR);
        bank.addAccount("456", holder2, 2000.00, AccountType.VIP);

        // Create thread groups for VIP and regular clients
        ThreadGroup regularGroup = new ThreadGroup("Regular Clients");
        ThreadGroup vipGroup = new ThreadGroup("VIP Clients");

        // Initialize clients for different account types
        Client client1 = new Client(bank, "123", 200); // Assuming this is a regular client
        Client client2 = new Client(bank, "456", 300); // Assuming this is a VIP client

        // Create and start threads within the appropriate thread group
        Thread t1 = new Thread(regularGroup, client1, "Client 1 (Regular)");
        Thread t2 = new Thread(vipGroup, client2, "Client 2 (VIP)");

        t1.start();
        t2.start();

        // Wait for all threads to finish
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("A thread was interrupted.");
        }

    }
}