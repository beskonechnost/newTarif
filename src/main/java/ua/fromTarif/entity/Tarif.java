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
    private List<Prices> tarifPrices;

    public Tarif() {
    }

    public Tarif(Tarif tarif) {
        this.tarifName = new String(tarif.getTarifName());
        this.status = new Boolean(tarif.isStatus());
        this.tarifPrices = tarif.cloneThisTarifListTestPrice();
    }

    public Tarif(String testPriceName) {
        this.tarifName = testPriceName;
        this.tarifPrices = new ArrayList<Prices>() {
        };
        this.status=true;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Tarif(String testPriceName, List<Prices> tarifPrices) {
        this.tarifName = testPriceName;
        this.tarifPrices = tarifPrices;
        this.status=true;

    }

    public void addPrice(Prices price){
        tarifPrices.add(price);
    }

    public void deletePriceForName(String priceName){
        Prices p1= null;
        Iterator<Prices> iterator = tarifPrices.iterator();
            while (iterator.hasNext()){
                Prices p = iterator.next();
                if(p.getName().equals(priceName)) p1=p;
            }
        tarifPrices.remove(p1);
    }

    public void setPriceForName(String priceName, Double newPrice){
        Prices p1= null;
        Iterator<Prices> iterator = tarifPrices.iterator();
        while (iterator.hasNext()){
            Prices p = iterator.next();
            if(p.getName().equals(priceName)) p1=p;
        }
        p1.setPrice(newPrice);
    }

    public void deletePrice(Prices price){
        tarifPrices.remove(price);
    }

    public List<Prices> getTarifPrices() {
        return tarifPrices;
    }

    public void setTarifPrices(List<Prices> tarifPrices) {
        this.tarifPrices = tarifPrices;
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
                ", tarifPrices=" + tarifPrices +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return (Tarif)super.clone();
    }

    public List<Prices> cloneThisTarifListTestPrice() {
        List<Prices> clonedList = new ArrayList<Prices>(tarifPrices.size());
        for (Prices prices : tarifPrices) {
            clonedList.add(new Prices(prices));
        }
        return clonedList;
    }
}
