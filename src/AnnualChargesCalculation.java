
public class AnnualChargesCalculation implements Runnable {
    private Bank bank;
    private double charges;

    public AnnualChargesCalculation(Bank bank, double charges) {
        this.bank = bank;
        this.charges = charges;
    }

    @Override
    public void run() {
        // Apply annual charges to all accounts in the bank
        for (BankAccount account : bank.getAllAccounts()) {
            account.applyAnnualCharges(charges);
        }
    }
}
