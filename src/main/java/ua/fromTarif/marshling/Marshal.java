package ua.fromTarif.marshling;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.extended.EncodedByteArrayConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.log4j.Logger;
import ua.fromTarif.controller.command.LoginCommand;
import ua.fromTarif.entity.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Андрей on 16.05.2017.
 */
public class Marshal {

    public static void marshallerLombard(List<Lombard> object, String nameXmlFile) throws IOException {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias(nameXmlFile, List.class);
        xStream.processAnnotations(Lombard.class);
        String xml = xStream.toXML(object);
        saveToFile(xml, nameXmlFile);
    }

    public static void marshallerUser(List<User> object, String nameXmlFile) throws IOException {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias(nameXmlFile, List.class);
        xStream.processAnnotations(User.class);
        String xml = xStream.toXML(object);
        saveToFile(xml, nameXmlFile);
    }


    private static void saveToFile(String xml, String nameFile) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(nameFile+".xml")));
        bufferedWriter.write(xml);
        bufferedWriter.close();
    }

    public static List<Lombard> unmarshallingLombard(File file) throws IOException, ClassNotFoundException {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("All", List.class);
        xStream.alias("Lombard", Lombard.class);
        xStream.aliasAttribute(Lombard.class, "number", "number");
        xStream.aliasAttribute(Lombard.class, "region", "region");
        xStream.aliasAttribute(Lombard.class, "formType", "formType");
        xStream.alias("Type", Type.class);
        xStream.aliasAttribute(Type.class, "typeName", "typeName");
        xStream.alias("Tarif", Tarif.class);
        xStream.aliasAttribute(Tarif.class, "tarifName", "tarifName");
        xStream.aliasAttribute(Tarif.class, "status", "status");
        xStream.aliasField("ByWeight", Tarif.class, "tarifPrices");
        xStream.alias("Prices", Prices.class);
        xStream.aliasAttribute(Prices.class, "name", "name");
        xStream.aliasAttribute(Prices.class, "price", "price");
        xStream.aliasAttribute(Prices.class, "testPrice", "testPrice");

        xStream.registerConverter((Converter) new EncodedByteArrayConverter());
        return (ArrayList<Lombard>) xStream.fromXML(file);
    }

    public static List<User> unmarshallingUser(File file) throws IOException, ClassNotFoundException {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("Users", List.class);
        xStream.alias("User", User.class);
        xStream.aliasAttribute(User.class, "login", "login");
        xStream.aliasAttribute(User.class, "pass", "pass");

        xStream.registerConverter((Converter) new EncodedByteArrayConverter());
        return (ArrayList<User>) xStream.fromXML(file);
    }
}
