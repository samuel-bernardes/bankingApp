package classes.accounts;

import classes.abstractClass.Account;

public class CheckingAccount extends Account {

    private final double manageTax;

    public CheckingAccount(String agencyId, String accountId) {
        super(agencyId, accountId);
        manageTax = 0.05;
    }

    @Override
    public void deposit(double amount) {
        this.setBalance(this.getBalance() + amount);
    }

    @Override
    public void withdraw(double amount) {
        this.setBalance(this.getBalance() - amount);
    }

    public double getManageTax() {
        return manageTax;
    }
}
