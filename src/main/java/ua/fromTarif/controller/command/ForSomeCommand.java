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

        String select[] = request.getParameterValues("choose");

        List<String> sel = new ArrayList<String>(Arrays.asList(select));

        if (select == null && select.length == 0) {
            throw new AppException("Нет выбраных ЛО для изменения цены!");
        }

        List<Lombard> lombards = (List<Lombard>) session.getAttribute("lombards");


        StringBuffer nameAction = new StringBuffer();
        nameAction.append("Для_");

        List<Lombard> selectLombards = new ArrayList<Lombard>();
        Type newType=null;
        for(int i = 0; i<select.length; i++){
            nameAction.append(select[i]);
            nameAction.append("_");
            for(Lombard l : lombards){
                if(newType==null){
                    if(l.getTypeTarif().getTypeName().equals("Standart")){

                        //////////////////////

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ObjectOutputStream ous = new ObjectOutputStream(baos);
                        ous.writeObject(l.getTypeTarif());
                        ous.close();
                        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                        ObjectInputStream ois = new ObjectInputStream(bais);

                        try {
                            newType = (Type) ois.readObject();
                        } catch (ClassNotFoundException e) {
                            throw new AppException("ClassNotFoundException");
                        }


                        ///////////////////////////

                        LOG.debug("l ===> "+l.getTypeTarif().toString());
                        LOG.debug("l ===> "+l.getTypeTarif().hashCode());
                        LOG.debug("l name ===> "+l.getTypeTarif().getTypeName().hashCode());
                        LOG.debug("newType ===> "+newType.toString());
                        LOG.debug("newType ===> "+newType.hashCode());
                        LOG.debug("newType name ===> "+newType.getTypeName().hashCode());

                    }
                }
                if(select[i].equals(l.getNumber())){
                    selectLombards.add(l);
                    break;
                }
            }
            continue;
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
