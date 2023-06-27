package classes.clients;

import java.text.SimpleDateFormat;
import java.util.Date;

import classes.abstractClass.Client;

public class NaturalPerson extends Client {

    private String cpf;

    public NaturalPerson(String fullName, String address, String cpf, String fantasyName) {
        super(fullName, address);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
