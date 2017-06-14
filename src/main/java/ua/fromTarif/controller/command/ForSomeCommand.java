package ua.fromTarif.controller.command;

import ua.fromTarif.controller.fromCommand.Command;
import ua.fromTarif.controller.path.Path;
import ua.fromTarif.entity.Lombard;
import ua.fromTarif.entity.Tarif;
import ua.fromTarif.entity.Type;
import ua.fromTarif.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

import org.apache.log4j.Logger;

/**
 * Created by Андрей on 01.06.2017.
 */
public class ForSomeCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ForSomeCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        HttpSession session = request.getSession(true);

        List<String> sel = new ArrayList<String>(Arrays.asList(request.getParameterValues("choose")));

        if (sel == null && sel.size() == 0) {
            throw new AppException("Нет выбраных ЛО для изменения цены!");
        }

        List<Lombard> lombards = (List<Lombard>) session.getAttribute("lombards");
        List<Lombard> fromType = (List<Lombard>) session.getAttribute("fromType");
        LOG.debug("fromType ===> " + fromType.toString());

        StringBuffer nameAction = new StringBuffer();
        nameAction.append("Для_");

        List<Lombard> selectLombards = new ArrayList<Lombard>();
        Type newType=null;

        for(String s : sel){
            nameAction.append(s);
            nameAction.append("_");

            for(Lombard l : lombards){
                if(s.equals(l.getNumber())){
                    selectLombards.add(l);
                    break;
                }
            }
            continue;
        }

        for(Lombard l : lombards){

            if(newType==null){

                for(Lombard lft : fromType) {

                    if (lft.getNumber().equals("Form Type Standart")) {

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ObjectOutputStream ous = new ObjectOutputStream(baos);
                        ous.writeObject(lft.getTypeTarif());
                        ous.close();
                        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                        ObjectInputStream ois = new ObjectInputStream(bais);

                        try {
                            newType = (Type) ois.readObject();
                            newType.setTypeName(String.valueOf(nameAction));
                        } catch (ClassNotFoundException e) {
                            throw new AppException("ClassNotFoundException");
                        }


                        ///////////////////////////

                        LOG.debug("lft string ===> " + l.getTypeTarif().toString());
                        LOG.debug("lft hash ===> " + l.getTypeTarif().hashCode());
                        LOG.debug("lft name ===> " + l.getTypeTarif().getTypeName().hashCode());
                        LOG.debug("newType string ===> " + newType.toString());
                        LOG.debug("newType hash ===> " + newType.hashCode());
                        LOG.debug("newType name ===> " + newType.getTypeName().hashCode());

                    }
                }
            }
        }

        newType.setTypeName(String.valueOf(nameAction));
        List<Tarif> standart = newType.getTarifs();


        request.setAttribute("nameAction", nameAction);
        request.setAttribute("standart", standart);

        session.setAttribute("newType", newType);
        LOG.debug("newType 1 ===> "+newType);


        session.setAttribute("sel", sel);

        return Path.FOR_SOME;
    }
}
