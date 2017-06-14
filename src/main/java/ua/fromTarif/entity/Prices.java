package ua.fromTarif.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.io.Serializable;

/**
 * Created by Андрей on 16.05.2017.
 */
@XStreamAlias("Prices")
public class Prices implements Serializable{

    @XStreamAsAttribute
    private String name;

    @XStreamAsAttribute
    private int testPrice;

    @XStreamAsAttribute
    private double price;

    public Prices() {
    }

    public Prices(String name, double price, int testPrice) {
        this.name = name;
        this.price = price;
        this.testPrice = testPrice;
    }

    public Prices(Prices prices) {
        this.name = new String(prices.getName());
        this.price = new Double(prices.getPrice());
        this.testPrice = new Integer(prices.getTestPrice());
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

    public int getTestPrice() {
        return testPrice;
    }

    public void setTestPrice(int testPrice) {
        this.testPrice = testPrice;
    }

    @Override
    public String toString() {
        return "Prices{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return (Prices)super.clone();
    }
}
