package ATM.Exceptions;

public class AccountNotExistException extends RuntimeException{

    public AccountNotExistException(){super("Account does not Exist!");}

}
