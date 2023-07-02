package classes.abstractClass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public abstract class Client {
    private int clientId;
    private String fullName;
    private String email;
    private String password;
    private String registerDate;
    private String adress;
    private Account account;

    private static Random random = new Random();

    private static String getCurrentDate() {

        Date currentDate = new Date();

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        String formatedDate = format.format(currentDate);

        return formatedDate;
    }

    public Client(String fullName, String adress, String password, String email) {
        this.clientId = random.nextInt(100);
        this.fullName = fullName;
        this.registerDate = getCurrentDate();
        this.adress = adress;
        this.email = email;
        this.password = password;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
