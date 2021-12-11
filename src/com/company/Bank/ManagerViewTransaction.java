package com.company.Bank;

import com.company.Persons.Manager;
import com.company.Stock.StockMarket;
import com.company.Transactions.Transaction;
import com.company.Utils.Parser;
import com.company.Utils.Writer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class ManagerViewTransaction {

    public static void run(Manager manager, Currency currency, StockMarket stockMarket) throws IOException {

        Scanner input = new Scanner(System.in);
        Parser parser = new Parser();

        System.out.println();
        System.out.println("Enter date : ( YYYY/MM/DD ) ");
        String date1 = input.next();
        LocalDate date = LocalDate.parse(date1);
        List<Transaction> transactions = parser.parseTxnByDate(date);
        int count = 1;

        System.out.println();
        System.out.println("********************************************************************************************");
        System.out.println("        Transaction ID      Date       Amount      Customer ID      Type ");
        for (Transaction transaction : transactions){
            System.out.println("<" + count + ">" + "    " + transaction.getId() + "   " + transaction.getTimestamp() + "    " + transaction.getAmount() + "    " + transaction.getPersonId() + "    " + transaction.getType());
            count += 1;
        }
        System.out.println("********************************************************************************************");

        ManagerOptions.options(manager, currency, stockMarket);

    }
}
