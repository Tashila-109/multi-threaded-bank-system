public class InterestCalculator implements Runnable {
    private Bank bank;

    public InterestCalculator(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void run() {
        System.out.println("Calculating interest for all accounts...");
        bank.applyInterestToAllAccounts();
    }
}
