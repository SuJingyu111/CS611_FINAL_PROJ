package ATM.Account;

public class Account {
    //TODO

    private String ownerName;

    private int ownerId;

    private String pwd;

    private int balance;

    private AccountType TYPE;

    public Account(String ownerName, int ownerId, String pwd, int balance) {
        this.ownerName = ownerName;
        this.ownerId = ownerId;
        this.pwd = pwd;
        this.balance = balance;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public AccountType getTYPE() {
        return TYPE;
    }

    public void setTYPE(AccountType TYPE) {
        this.TYPE = TYPE;
    }
}
