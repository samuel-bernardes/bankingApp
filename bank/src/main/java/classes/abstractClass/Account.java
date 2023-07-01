package classes.abstractClass;

public abstract class Account {

    private static int accNumber;
    private double balance;
    private String agencyId;
    private String accountId;

    public abstract void deposit(double amount);

    public abstract void withdraw(double amount);

    public Account(String agencyId, String accountId) {
        accNumber++;
        this.balance = 0;
        this.agencyId = agencyId;
        this.accountId = accountId;
    }

    public static void setAccNumber(int accNumber) {
        Account.accNumber = accNumber;
    }

    public static int getAccNumber() {
        return accNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

}
