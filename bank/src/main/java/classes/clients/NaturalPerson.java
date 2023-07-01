package classes.clients;

import classes.abstractClass.Client;

public class NaturalPerson extends Client {

    private String cpf;

    public NaturalPerson(String fullName, String address, String email, String password, String cpf) {
        super(fullName, address, password, email);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
