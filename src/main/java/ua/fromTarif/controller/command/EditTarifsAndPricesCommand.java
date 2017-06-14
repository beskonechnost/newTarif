package ua.fromTarif.controller.command;

import org.apache.log4j.Logger;
import ua.fromTarif.controller.fromCommand.Command;
import ua.fromTarif.controller.path.Path;
import ua.fromTarif.entity.*;
import ua.fromTarif.marshling.Marshal;
import ua.fromTarif.exception.AppException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Андрей on 07.06.2017.
 */
public class EditTarifsAndPricesCommand extends Command {

    private static final Logger LOG = Logger.getLogger(EditTarifsAndPricesCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        HttpSession session = request.getSession(true);

        List<String> sel = (List<String>) session.getAttribute("sel");
        Type newType = (Type) session.getAttribute("newType");
        Integer i = Integer.valueOf(request.getParameter("count"));
        LOG.debug("COUNT =================================================> "+i);
        String w = request.getParameter("weight|1");
        LOG.debug("weight 1 =================================================> "+w);
        String w1 = request.getParameter("weight|2");
        LOG.debug("weight 2 =================================================> "+w1);


        LOG.debug("newType 2 ===> "+newType);

        for(Tarif t : newType.getTarifs()){
            String nameTarif = t.getTarifName();
            for(Prices p : newType.getTarifForName(nameTarif).getTarifPrices()){
                String namePrice = p.getName();
                Double d = Double.valueOf(request.getParameter(nameTarif+"|"+namePrice));
                LOG.debug("===> "+d);
                p.setPrice(d);
            }
        }

        List<Lombard> lombards = (List<Lombard>) session.getAttribute("lombards");
        List<Lombard> fromType = (List<Lombard>) session.getAttribute("fromType");

        for(String s : sel){
            for(Lombard l : lombards){
                if(s.equals(l.getNumber())){
                    l.setTypeTarif(newType);
                    break;
                }
                continue;
            }
        }

        List<Lombard> all = new ArrayList<Lombard>();
        for(Lombard l : lombards){
            all.add(l);
            LOG.debug("l ===> "+l);
        }
        for(Lombard l : fromType){
            all.add(l);
            LOG.debug("l ===> "+l);
        }

        session.setAttribute("lombards", lombards);

        String nameXmlFile = "All";
        Marshal.marshallerLombard(all, nameXmlFile);

        return Path.ALL_LOMBARD_PAGE;
    }
}
