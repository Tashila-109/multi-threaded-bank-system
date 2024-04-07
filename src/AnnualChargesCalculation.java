public class AnnualChargesCalculation implements Runnable {
    private Bank bank;

    public AnnualChargesCalculation(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void run() {
        System.out.println("Applying annual charges to all accounts...");
        bank.applyInterestToAllAccounts();
    }
}
