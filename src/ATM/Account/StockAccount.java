package ATM.Account;

public class StockAccount extends Account{

    public StockAccount(String ownerName, int ownerId, String pwd, int balance) {
        super(ownerName, ownerId, pwd, balance);
        setTYPE(AccountType.STOCK);
    }

}
