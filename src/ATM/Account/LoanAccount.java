package ATM.Account;

public class LoanAccount extends Account{

    public LoanAccount(String ownerName, int ownerId, String pwd, int balance) {
        super(ownerName, ownerId, pwd, balance);
        setTYPE(AccountType.LOAN);
    }

}
