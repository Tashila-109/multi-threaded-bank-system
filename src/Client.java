import Enums.AccountType;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        Bank bank = new Bank();

        String[] accountNumbers = {"123-456-789", "987-654-321", "456-789-123"};

        // Create account holders and accounts
        AccountHolder holder1 = new AccountHolder("John Doe", "ID123", "555-1234", "123 Elm St", "AB1234567");
        AccountHolder holder2 = new AccountHolder("Jane Doe", "ID456", "555-5678", "456 Oak Rd", "CD8901234");
        AccountHolder holder3 = new AccountHolder("Jim Beam", "ID789", "555-9012", "789 Pine Ave", "EF4567890");

        bank.addAccount(accountNumbers[0], holder1, 1000.00, AccountType.SAVINGS, 3.5, 0);
        bank.addAccount(accountNumbers[1], holder2, 2000.00, AccountType.CURRENT, 0, 500);
        bank.addAccount(accountNumbers[2], holder3, 5000.00, AccountType.FIXED_DEPOSIT, 8, 0);

        // Create thread groups for VIP and regular clients and Bank Managers
        ThreadGroup bankManagerGroup = new ThreadGroup("Bank Managers");
        ThreadGroup regularClientGroup = new ThreadGroup("Regular Clients");
        ThreadGroup vipClientGroup = new ThreadGroup("VIP Clients");

        // List to hold all threads
        List<Thread> threads = new ArrayList<>();

        // Bank Manager tasks
        Thread interestCalculatorThread = new Thread(bankManagerGroup, new InterestCalculator(bank), "InterestCalculator");
        Thread chargesCalculatorThread = new Thread(bankManagerGroup, new AnnualChargesCalculation(bank), "ChargesCalculator");
        threads.add(interestCalculatorThread);
        threads.add(chargesCalculatorThread);

        // Add client transactions to the list
        for (int i = 0; i < 10; i++) {
            ThreadGroup selectedGroup = (i % 2 == 0) ? vipClientGroup : regularClientGroup;

            // Cycle through the account numbers for each client
            String accountNumber = accountNumbers[i % accountNumbers.length];
            BankAccount account = bank.getAccount(accountNumber);

            double amount = 100 + (i * 100); // Example transaction amount
            boolean isDeposit = i % 2 == 0;

            Thread clientThread = new Thread(selectedGroup, new TransactionProcessor(account, amount, isDeposit), "Client" + i);
            threads.add(clientThread);
        }

        // Start all threads
        for (Thread thread : threads) {
            thread.start();
        }

        // Wait for all threads to finish
        try {
            // Wait for all threads to finish
            for (Thread thread : threads) {
                thread.join(); // Wait for each thread to finish
            }
        } catch (InterruptedException e) {
            System.out.println("A thread was interrupted.");
        }

        // Output the final account balances
        System.out.println("Final balances:");
        for (BankAccount account : bank.getAllAccounts()) {
            System.out.println(account.getAccountNumber() + ": " + account.getBalance());
        }
    }
}