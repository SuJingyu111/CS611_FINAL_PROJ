package com.company.Bank.CustomerAllOptions;

import com.company.Account.Account;
import com.company.Account.AccountType;
import com.company.Account.StockAccount;
import com.company.Currency.Currency;
import com.company.Currency.CurrencyType;
import com.company.Persons.Customer;
import com.company.Stock.StockMarket;
import com.company.Transactions.StockTxn;
import com.company.Transactions.Transaction;
import com.company.Utils.Parser;
import com.company.Utils.Writer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class CustomerStock {

    public static void run(Customer customer, Currency currency, StockMarket stockMarket) throws IOException {

        Parser parser = new Parser();
        Scanner input = new Scanner(System.in);
        Writer writer = new Writer();

        Map<String, Double> allStocks = parser.parseAllStockInfo();
        System.out.println();
        System.out.println("STOCK MARKET : ");
        System.out.println("********************************************************************************************");
        System.out.println("    Stock        Price ");
        int count = 1;
        for (Map.Entry<String, Double> entry : allStocks.entrySet()){
            System.out.println("<" + count + "> " + entry.getKey() + "         " + entry.getValue());
            count += 1;
        }
        System.out.println("********************************************************************************************");
        System.out.println();

        List<Account> stockAccounts = customer.getAccountsByType(AccountType.STOCK);
        Account stockAccount = stockAccounts.get(0);
        Map<String, Integer> sharesHolding = ((StockAccount) stockAccount).getSharesHolding();


        System.out.println("Current Shares : ");
        System.out.println("********************************************************************************************");
        System.out.println("    Stock        Shares ");
        count = 1;
        for (Map.Entry<String, Integer> entry : sharesHolding.entrySet()){
            System.out.println("<" + count + "> " + entry.getKey() + "         " + entry.getValue());
        }
        System.out.println("********************************************************************************************");
        System.out.println();

        System.out.println("Do you want to :");
        System.out.println("<1> Buy");
        System.out.println("<2> Sell");
        String choice1 = input.next();

        while(!choice1.equals("1") && !choice1.equals("2")){
            System.out.println();
            System.out.println("You've entered an incorrect input !");
            System.out.println("Do you want to :");
            System.out.println("<1> Buy");
            System.out.println("<2> Sell");
            choice1 = input.next();
        }
        
        Map<String, Integer> purchaseInfo = new HashMap<>();
        Double price = null;

        if(choice1.equals("1")){

            System.out.println();
            System.out.println("Enter Name of Stock :");
            String name = input.next();

            System.out.println("Enter No of Shares : ");
            int noOfShares = input.nextInt();

            System.out.println("Enter Type of Currency :");
            System.out.println("<1> USD");
            System.out.println("<2> EUR");
            System.out.println("<3> CAD");
            System.out.println("<4> JPY");
            String choice2 = input.next();

            while(!choice2.equals("1") && !choice2.equals("2") && !choice2.equals("3") && !choice2.equals("4")){
                System.out.println();
                System.out.println("You've entered an incorrect input !");
                System.out.println("<1> USD");
                System.out.println("<2> EUR");
                System.out.println("<3> CAD");
                System.out.println("<4> JPY");
                choice2 = input.next();
            }

            if(choice2.equals("1")){((StockAccount) stockAccount).buyShare(name, noOfShares, CurrencyType.USD);}
            else if(choice2.equals("2")){((StockAccount) stockAccount).buyShare(name, noOfShares, CurrencyType.EUR);}
            else if(choice2.equals("3")){((StockAccount) stockAccount).buyShare(name, noOfShares, CurrencyType.CAD);}
            else if(choice2.equals("4")){((StockAccount) stockAccount).buyShare(name, noOfShares, CurrencyType.JPY);}

            price = allStocks.get(name);
            purchaseInfo.put(name, noOfShares);
            writer.updateAccountToDisk(stockAccount);

        }

        else if(choice1.equals("2")){

            System.out.println("Enter Name of Stock :");
            String name = input.next();

            System.out.println("Enter No of Shares : ");
            int noOfShares = input.nextInt();

            ((StockAccount) stockAccount).sellShare(name, noOfShares);
            price = allStocks.get(name);
            purchaseInfo.put(name, -noOfShares);
            writer.updateAccountToDisk(stockAccount);

        }

        System.out.println("Current Shares : ");
        System.out.println("********************************************************************************************");
        System.out.println("    Stock        Shares ");
        count = 1;
        for (Map.Entry<String, Integer> entry : sharesHolding.entrySet()){
            System.out.println("<" + count + "> " + entry.getKey() + "         " + entry.getValue());
        }
        System.out.println("********************************************************************************************");
        System.out.println();

        writer.writeTxn(recordTransaction(price ,customer.getId(),purchaseInfo));
        CustomerOptions.options(customer, currency, stockMarket);

    }

    public static Transaction recordTransaction(Double amount, String cusID, Map<String, Integer> purchaseInfo){

        String ID = getRandomNumberString();
        String customerID = cusID;
        Double value = amount;
        LocalDate date = LocalDate.now();
        StockTxn transaction = new StockTxn(ID, date, amount, customerID, purchaseInfo);
        return transaction;
    }

    public static String getRandomNumberString() {

        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }
}
