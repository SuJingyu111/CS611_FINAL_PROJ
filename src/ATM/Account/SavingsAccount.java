package ATM.Account;

public class SavingsAccount extends Account{
    //TODO

    public SavingsAccount(String ownerName, int ownerId, String pwd, int balance) {
        super(ownerName, ownerId, pwd, balance);
        setTYPE(AccountType.SAVINGS);
    }

}
