package classes.accounts;

import classes.abstractClass.Account;

public class CheckingAccount extends Account {

    public CheckingAccount(String agencyId, String accountId) {
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
