package ua.fromTarif;

import org.apache.log4j.Logger;
import ua.fromTarif.controller.command.ForSomeCommand;
import ua.fromTarif.entity.*;
import ua.fromTarif.marshling.Marshal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Андрей on 16.05.2017.
 */
public class Test {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Prices p11 = new Prices("less 6", 100);
        Prices p21 = new Prices("more 6 and less or equal 10", 200);
        Prices p31 = new Prices("more 10 and less or equal 25", 300);
        Prices p41 = new Prices("more 25", 400.1);
        List<Prices> prises1 = new ArrayList<Prices>();
        prises1.add(p11);
        prises1.add(p21);
        prises1.add(p31);
        prises1.add(p41);


        Prices p12 = new Prices("less 6", 999);
        Prices p22 = new Prices("more 6 and less or equal 10", 888);
        Prices p32 = new Prices("more 10 and less or equal 25", 777);
        Prices p42 = new Prices("more 25", 400.1);
        List<Prices> prises2 = new ArrayList<Prices>();
        prises2.add(p12);
        prises2.add(p22);
        prises2.add(p32);
        prises2.add(p42);

        Prices p13 = new Prices("less 6", 999);
        Prices p23 = new Prices("more 6 and less or equal 10", 888);
        Prices p33 = new Prices("more 10 and less or equal 25", 777);
        Prices p43 = new Prices("more 25", 400.1);
        List<Prices> prises3 = new ArrayList<Prices>();
        prises3.add(p13);
        prises3.add(p23);
        prises3.add(p33);
        prises3.add(p43);

        Tarif t1 = new Tarif("ABC", prises1);
        Tarif t2 = new Tarif("MIN", prises2);
        Tarif t3 = new Tarif("MAX", prises3);

        /////////////////////////////////////////////////////////////

        Prices p111 = new Prices("less 6", 110);
        Prices p211 = new Prices("more 6 and less or equal 10", 210);
        Prices p311 = new Prices("more 10 and less or equal 25", 310);
        Prices p411 = new Prices("more 25", 410.1);
        List<Prices> prises11 = new ArrayList<Prices>();
        prises11.add(p111);
        prises11.add(p211);
        prises11.add(p311);
        prises11.add(p411);

        Prices p122 = new Prices("less 6", 9991);
        Prices p222 = new Prices("more 6 and less or equal 10", 8881);
        Prices p322 = new Prices("more 10 and less or equal 25", 7771);
        Prices p422 = new Prices("more 25", 4001.1);
        List<Prices> prises22 = new ArrayList<Prices>();
        prises22.add(p122);
        prises22.add(p222);
        prises22.add(p322);
        prises22.add(p422);

        Prices p133 = new Prices("less 6", 9992);
        Prices p233 = new Prices("more 6 and less or equal 10", 8882);
        Prices p333 = new Prices("more 10 and less or equal 25", 7772);
        Prices p433 = new Prices("more 25", 4002.1);
        List<Prices> prises33 = new ArrayList<Prices>();
        prises33.add(p133);
        prises33.add(p233);
        prises33.add(p333);
        prises33.add(p433);

        Tarif t11 = new Tarif("ABC", prises11);
        Tarif t21 = new Tarif("MIN", prises22);
        Tarif t31 = new Tarif("MAX", prises33);

        ///////////////////////////////////////////////////////


        List<Tarif> tarifs1 = new ArrayList<Tarif>();
        tarifs1.add(t1);
        tarifs1.add(t2);
        tarifs1.add(t3);

        List<Tarif> tarifs2 = new ArrayList<Tarif>();
        tarifs2.add(t11);
        tarifs2.add(t21);
        tarifs2.add(t31);

        Type type1 = new Type("Standart", tarifs1);
        Type type2 = new Type("New", tarifs2);

        Lombard l1 = new Lombard("001", "Харьков 1", type1);
        Lombard l2 = new Lombard("002", "Харьков 1", type2);
        Lombard l3 = new Lombard("003", "Киев", type1);

        List<Lombard> list = new ArrayList<Lombard>();
        list.add(l1);
        list.add(l2);
        list.add(l3);


        /*String nameXmlFile = "All";
        Marshal.marshallerLombard(list, nameXmlFile);

        List<Lombard> lombards = Marshal.unmarshallingLombard(new File(nameXmlFile.concat(".xml")));

        for (Lombard l : lombards) {
            System.out.println(l);
        }
        System.out.println();
        */
        //
        /*
        List<User> u = new ArrayList<User>();
        u.add(new User("User", "myday"));
        String nameXmlFileUsers = "Users";
        Marshal.marshallerUser(u, nameXmlFileUsers);

        //

        List<User> users = Marshal.unmarshallingUser(new File(nameXmlFileUsers.concat(".xml")));
        for(User u1 : users){
            System.out.println(u1);
        }
    */
    }
}
