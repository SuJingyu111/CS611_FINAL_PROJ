package ATM.Account;

public class CheckingsAccount extends Account{
    //TODO

    public CheckingsAccount(String ownerName, int ownerId, String pwd, int balance) {
        super(ownerName, ownerId, pwd, balance);
        setTYPE(AccountType.CHECKINGS);
    }

}
