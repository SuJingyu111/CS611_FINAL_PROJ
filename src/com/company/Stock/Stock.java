package com.company.Stock;

public class Stock {

    private String corpName;

    private double price;

    public Stock(String corpName, double price) {
        this.corpName = corpName;
        this.price = price;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return corpName + "," + price;
    }
}
