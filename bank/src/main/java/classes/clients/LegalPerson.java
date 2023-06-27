package classes.clients;

import classes.abstractClass.Client;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LegalPerson extends Client {

    private String cnpj;
    private String fantasyName;

    public LegalPerson(String fullName, String address, String cnpj, String fantasyName) {
        super(fullName, address);
        this.cnpj = cnpj;
        this.fantasyName = fantasyName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getFantasyName() {
        return fantasyName;
    }

    public void setFantasyName(String fantasyName) {
        this.fantasyName = fantasyName;
    }

}
