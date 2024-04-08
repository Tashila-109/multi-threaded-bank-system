import Enums.AccountType;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        Bank bank = new Bank();

        String[] accountNumbers = {
                "123-456-789", "987-654-321", "456-789-123",
                "111-222-333", "222-333-444", "333-444-555",
                "444-555-666", "555-666-777", "666-777-888",
                "777-888-999"
        };

        // Create account holders and accounts and add the accounts into the bank
        AccountHolder holder1 = new AccountHolder("John Doe", "ID123", "555-1234", "123 Elm St", "AB1234567");
        AccountHolder holder2 = new AccountHolder("Jane Doe", "ID456", "555-5678", "456 Oak Rd", "CD8901234");
        AccountHolder holder3 = new AccountHolder("Jim Beam", "ID789", "555-9012", "789 Pine Ave", "EF4567890");
        AccountHolder holder4 = new AccountHolder("Alice Smith", "ID234", "555-2345", "234 Birch St", "GH1234568");
        AccountHolder holder5 = new AccountHolder("Bob Johnson", "ID345", "555-3456", "345 Cedar St", "IJ2345679");
        AccountHolder holder6 = new AccountHolder("Carol White", "ID456", "555-4567", "456 Dogwood St", "KL3456780");
        AccountHolder holder7 = new AccountHolder("David Brown", "ID567", "555-5678", "567 Elm St", "MN4567891");
        AccountHolder holder8 = new AccountHolder("Eve Davis", "ID678", "555-6789", "678 Fir St", "OP5678902");
        AccountHolder holder9 = new AccountHolder("Frank Green", "ID789", "555-7890", "789 Grove St", "QR6789013");
        AccountHolder holder10 = new AccountHolder("Grace Hall", "ID890", "555-8901", "890 Hill St", "ST7890124");

        bank.addAccount(accountNumbers[0], holder1, 1000.00, AccountType.SAVINGS, 3.5, 0);
        bank.addAccount(accountNumbers[1], holder2, 2000.00, AccountType.CURRENT, 0, 500);
        bank.addAccount(accountNumbers[2], holder3, 5000.00, AccountType.FIXED_DEPOSIT, 8, 0);
        bank.addAccount(accountNumbers[3], holder4, 1500.00, AccountType.SAVINGS, 2.5, 0);
        bank.addAccount(accountNumbers[4], holder5, 2500.00, AccountType.CURRENT, 0, 1000);
        bank.addAccount(accountNumbers[5], holder6, 3500.00, AccountType.FIXED_DEPOSIT, 5, 0);
        bank.addAccount(accountNumbers[6], holder7, 4500.00, AccountType.SAVINGS, 3.0, 0);
        bank.addAccount(accountNumbers[7], holder8, 5500.00, AccountType.CURRENT, 0, 1500);
        bank.addAccount(accountNumbers[8], holder9, 6500.00, AccountType.FIXED_DEPOSIT, 4.5, 0);
        bank.addAccount(accountNumbers[9], holder10, 7500.00, AccountType.SAVINGS, 3.2, 0);

        // Create thread groups for VIP and regular clients and Bank Managers
        ThreadGroup bankManagerGroup = new ThreadGroup("Bank Managers");
        ThreadGroup accountManagerGroup = new ThreadGroup("Account Managers");
        ThreadGroup regularClientGroup = new ThreadGroup("Regular Clients");
        ThreadGroup vipClientGroup = new ThreadGroup("VIP Clients");

        // Set priorities to the groups
        bankManagerGroup.setMaxPriority(9);
        accountManagerGroup.setMaxPriority(8);
        regularClientGroup.setMaxPriority(5);
        vipClientGroup.setMaxPriority(10);

        // List to hold all threads
        List<Thread> threads = new ArrayList<>();

        // Bank Manager tasks
        Thread interestCalculatorThread = new Thread(accountManagerGroup, new InterestCalculator(bank), "InterestCalculator");
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

        for (BankAccount account : bank.getAllAccounts()) {
            BalanceReporter reporter = new BalanceReporter(account);
            Thread reportThread = new Thread(reporter);
            threads.add(reportThread);
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