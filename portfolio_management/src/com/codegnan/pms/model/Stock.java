package com.codegnan.pms.model;

/**
 * Represents a stock in the system.
 */
public class Stock {
    private int stockId;
    private String symbol;
    private String name;
    private double price;

    public Stock(int stockId, String symbol, String name, double price) {
        this.stockId = stockId;
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }

    public Stock(String symbol, String name, double price) {
        this(0, symbol, name, price);
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Stock [stockId=" + stockId + ", symbol=" + symbol + ", name=" + name + ", price=" + price + "]";
    }
}
