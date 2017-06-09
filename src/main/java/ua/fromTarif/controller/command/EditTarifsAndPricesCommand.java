package ua.fromTarif.controller.command;

import org.apache.log4j.Logger;
import ua.fromTarif.controller.fromCommand.Command;
import ua.fromTarif.controller.path.Path;
import ua.fromTarif.entity.Lombard;
import ua.fromTarif.entity.Prices;
import ua.fromTarif.entity.Tarif;
import ua.fromTarif.entity.Type;
import ua.fromTarif.exception.AppException;
import ua.fromTarif.marshling.Marshal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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

        LOG.debug("newType 2 ===> "+newType);

        for(Tarif t : newType.getTarifs()){
            String nameTarif = t.getTarifName();
            for(Prices p : newType.getTarifForName(nameTarif).getTestPrice()){
                String namePrice = p.getName();
                Double d = Double.valueOf(request.getParameter(nameTarif+"|"+namePrice));
                LOG.debug("===> "+d);
                p.setPrice(d);
            }
        }

        List<Lombard> lombards = (List<Lombard>) session.getAttribute("lombards");

        for(String s : sel){
            for(Lombard l : lombards){
                if(s.equals(l.getNumber())){
                    l.setTypeTarif(newType);
                    break;
                }
                continue;
            }
        }

        for(Lombard l : lombards){
            LOG.debug("l ===> "+l);
        }

        Marshal.marshallerLombard(lombards, "All2");
        session.setAttribute("lombards", lombards);


        return Path.ALL_LOMBARD_PAGE;
    }
}
