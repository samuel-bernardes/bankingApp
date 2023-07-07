package classes.Exceptions;

public class AccountNotFound extends Exception {
    public AccountNotFound(){
        super("Nao tem uma conta vinculado com seu usuario");
    }
}
