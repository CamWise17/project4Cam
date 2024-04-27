public class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(double initialBalance, double interestRate) {
        super(initialBalance);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void calculateInterest() {
        double interest = getBalance() * interestRate / 100;
        deposit(interest);
    }

    @Override
    public String toString() {
        return "SavingsAccount{" +
                "balance=" + getBalance() +
                ", interestRate=" + interestRate +
                '}';
    }
}
