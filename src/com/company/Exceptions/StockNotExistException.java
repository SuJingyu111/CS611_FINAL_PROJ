package com.company.Exceptions;

public class StockNotExistException extends RuntimeException{

    public StockNotExistException() {
        super("Stock does not exist!");
    }

}
