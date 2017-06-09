package ua.fromTarif.controller.command;

import org.apache.log4j.Logger;
import ua.fromTarif.controller.fromCommand.Command;
import ua.fromTarif.controller.path.Path;
import ua.fromTarif.entity.Lombard;
import ua.fromTarif.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Андрей on 26.05.2017.
 */
public class LombardTarifCommand extends Command {
    private static final long serialVersionUID = 5284656512621197471L;

    private static final Logger LOG = Logger.getLogger(LombardTarifCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        HttpSession se = request.getSession(true);

        String loNumber = request.getParameter("numberLombard");
        List<Lombard> lombards = (List<Lombard>) se.getAttribute("lombards");
        LOG.trace("lombards == > "+ lombards);

        for(Lombard lombard : lombards){
            LOG.trace("loNumber == > "+ loNumber);
            if(lombard.getNumber().equals(loNumber)){
                request.setAttribute("lombardNumber", lombard.getNumber());
                LOG.trace("1 == > "+ lombard.getNumber());
                //request.setAttribute("lombardTarifs", lombard.getTarifs());
                //LOG.trace("2 == > "+ lombard.getTarifs());
                break;
            }
        }

        return Path.ALL_TARIF_THIS_LOMBARD;
    }
}
