package ua.fromTarif.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Андрей on 30.05.2017.
 */
@XStreamAlias("Type")
public class Type implements Serializable{

    @XStreamAsAttribute
    private String typeName;

    private List<Tarif> tarifs;

    public Type(Type type1) {
    }

    public Type(String typeName) {
        this.typeName = typeName;
    }

    public Type(String typeName, List<Tarif> tarifs) {
        this.typeName = typeName;
        this.tarifs = tarifs;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<Tarif> getTarifs() {
        return tarifs;
    }

    public Tarif getTarifForName(String name) {
        Tarif my = null;
        for(Tarif t : tarifs){
            if(t.getTarifName().equals(name)){
                my = t;
            }
        }
        return my;
    }

    public void setTarifs(List<Tarif> tarifs) {
        this.tarifs = tarifs;
    }

    public void addTestPrice(Tarif testPrice){
        tarifs.add(testPrice);
    }

    public void deleteTestPriceForName(String testPriceName){
        Tarif t1= null;
        Iterator<Tarif> iterator = tarifs.iterator();
        while (iterator.hasNext()){
            Tarif t = iterator.next();
            if(t.getTarifName().equals(testPriceName)) t1=t;
        }
        tarifs.remove(t1);
    }

    public void deleteTestPrice(Tarif tarif){
        tarifs.remove(tarif);
    }

    @Override
    public String toString() {
        return "Type{" +
                "typeName='" + typeName + '\'' +
                ", tarifs=" + tarifs +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return (Type)super.clone();
    }

    public List<Tarif> cloneThisTypeListTarifs() {
        List<Tarif> clonedList = new ArrayList<Tarif>(tarifs.size());
        for (Tarif tarif : tarifs) {
            clonedList.add(new Tarif(tarif));
        }
        return clonedList;
    }
}
