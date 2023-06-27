package classes.abstractClass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public abstract class Client {
    private int clientId;
    private String fullName;
    private String registerDate;
    private String adress;

    private static Random random = new Random();

    private static String getCurrentDate() {

        Date currentDate = new Date();

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        String formatedDate = format.format(currentDate);

        return formatedDate;
    }

    public Client(String fullName, String adress) {
        this.clientId = random.nextInt(100);
        this.fullName = fullName;
        this.registerDate = getCurrentDate();
        this.adress = adress;
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

}
