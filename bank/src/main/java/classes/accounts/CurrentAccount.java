package classes.accounts;

import classes.abstractClass.Account;

public class CurrentAccount extends Account {

    public CurrentAccount(String agencyId, String accountId) {
        super(agencyId, accountId);
    }

    @Override
    public void deposit(double amount) {
        this.setBalance(this.getBalance() + amount);
    }

    @Override
    public void withdraw(double amount) {
        this.setBalance(this.getBalance() - amount);
    }

}
