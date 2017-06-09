package ua.fromTarif.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Андрей on 16.05.2017.
 */
@XStreamAlias("Tarif")
public class Tarif implements Serializable{

    @XStreamAsAttribute
    private String tarifName;

    @XStreamAsAttribute
    private boolean status;

    @XStreamAlias("ByWeight")
    private List<Prices> testPrice;

    public Tarif() {
    }

    public Tarif(Tarif tarif) {
        this.tarifName = new String(tarif.getTarifName());
        this.status = new Boolean(tarif.isStatus());
        this.testPrice = tarif.cloneThisTarifListTestPrice();
    }

    public Tarif(String testPriceName) {
        this.tarifName = testPriceName;
        this.testPrice = new ArrayList<Prices>() {
        };
        this.status=true;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Tarif(String testPriceName, List<Prices> testPrice) {
        this.tarifName = testPriceName;
        this.testPrice = testPrice;
        this.status=true;

    }

    public void addPrice(Prices price){
        testPrice.add(price);
    }

    public void deletePriceForName(String priceName){
        Prices p1= null;
        Iterator<Prices> iterator = testPrice.iterator();
            while (iterator.hasNext()){
                Prices p = iterator.next();
                if(p.getName().equals(priceName)) p1=p;
            }
        testPrice.remove(p1);
    }

    public void setPriceForName(String priceName, Double newPrice){
        Prices p1= null;
        Iterator<Prices> iterator = testPrice.iterator();
        while (iterator.hasNext()){
            Prices p = iterator.next();
            if(p.getName().equals(priceName)) p1=p;
        }
        p1.setPrice(newPrice);
    }

    public void deletePrice(Prices price){
        testPrice.remove(price);
    }

    public List<Prices> getTestPrice() {
        return testPrice;
    }

    public void setTestPrice(List<Prices> testPrice) {
        this.testPrice = testPrice;
    }

    public String getTarifName() {
        return tarifName;
    }

    public void setTarifName(String tarifName) {
        this.tarifName = tarifName;
    }

    @Override
    public String toString() {
        return "TestPrice{" +
                "tarifName='" + tarifName + '\'' +
                "status='" + status + '\'' +
                ", testPrice=" + testPrice +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return (Tarif)super.clone();
    }

    public List<Prices> cloneThisTarifListTestPrice() {
        List<Prices> clonedList = new ArrayList<Prices>(testPrice.size());
        for (Prices prices : testPrice) {
            clonedList.add(new Prices(prices));
        }
        return clonedList;
    }
}
