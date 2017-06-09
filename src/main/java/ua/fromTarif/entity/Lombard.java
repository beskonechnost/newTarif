package ua.fromTarif.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.*;

/**
 * Created by Андрей on 17.05.2017.
 */
@XStreamAlias("Lombard")
public class Lombard implements Comparable<Lombard>{

    @XStreamAsAttribute
    private String number;
    @XStreamAsAttribute
    private String region;

    private Type typeTarif;

    public Lombard() {
    }

    public Lombard(String number, String region, Type typeTarif) {
        this.number = number;
        this.region = region;
        this.typeTarif = typeTarif;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Type getTypeTarif() {
        return typeTarif;
    }

    public void setTypeTarif(Type typeTarif) {
        this.typeTarif = typeTarif;
    }

    @Override
    public String toString() {
        return "Lombard{" +
                "number='" + number + '\'' +
                ", region='" + region + '\'' +
                ", typeTarif=" + typeTarif +
                '}';
    }

    @Override
    public int compareTo(Lombard o) {
        return number.compareTo(o.getNumber());
    }
}
