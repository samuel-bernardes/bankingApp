package classes.accounts;

import classes.abstractClass.Account;

public class SavingAccount extends Account {

    private final double incomeBonus;

    public SavingAccount(String agencyId, String accountId) {
        super(agencyId, accountId);
        incomeBonus = 0.00514166666;
    }

    @Override
    public void deposit(double amount) {
        this.setBalance(this.getBalance() + amount);
    }

    @Override
    public void withdraw(double amount) {
        this.setBalance(this.getBalance() - amount);
    }

    public double getIncomeBonus() {
        return incomeBonus;
    }

}
