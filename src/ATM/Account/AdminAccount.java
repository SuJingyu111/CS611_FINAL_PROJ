package ATM.Account;

public class AdminAccount extends Account{

    public AdminAccount(String ownerName, int ownerId, String pwd, int balance) {
        super(ownerName, ownerId, pwd, balance);
        setTYPE(AccountType.ADMIN);
    }

}
