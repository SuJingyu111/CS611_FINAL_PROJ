package ATM.Exceptions;

public class AccountAlreadyExistException extends RuntimeException{

    public AccountAlreadyExistException() {super("Account of this type already exist!");}

}
