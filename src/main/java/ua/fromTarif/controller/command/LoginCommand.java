package ua.fromTarif.controller.command;

import org.apache.log4j.Logger;
import ua.fromTarif.controller.fromCommand.Command;
import ua.fromTarif.controller.path.Path;
import ua.fromTarif.entity.Lombard;
import ua.fromTarif.entity.Type;
import ua.fromTarif.entity.User;
import ua.fromTarif.exception.AppException;
import ua.fromTarif.marshling.Marshal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Андрей on 18.05.2017.
 */
public class LoginCommand extends Command {

    private static final long serialVersionUID = 4482536512627677471L;

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        HttpSession session = request.getSession(true);

        String login = request.getParameter("login");
        LOG.trace("Request parameter: loging --> " + login);

        String password = request.getParameter("password");
        if (login == null || password == null) {
            throw new AppException("Login/password cannot be empty");
        }

        String nameXmlFileUsers = "D:\\projects\\New\\newTarif\\Users";
        List<User> users = null;
        try {
            users = Marshal.unmarshallingUser(new File(nameXmlFileUsers.concat(".xml")));
        } catch (ClassNotFoundException e) {
            LOG.trace("не удалось найти файл с пользователями");
        }

        User user = new User(login, password);
        String forward = Path.PAGE_ERROR_PAGE;
        boolean flag = true;
        for (User u1 : users) {
            if(u1.equals(user)){
                forward = Path.ALL_LOMBARD_PAGE;
                try {
                    List<Lombard> lombards = Marshal.unmarshallingLombard(new File("D:\\projects\\New\\newTarif\\All.xml"));

/////////////////////

                    List<Type> type = new ArrayList<Type>();
                    for(Lombard l : lombards){
                        LOG.trace("number == "+ l.getNumber()+"type =="+l.getTypeTarif().hashCode());
                        LOG.trace("number == "+ l.getNumber()+"type =="+ l.getTypeTarif());
                        boolean f = true;
                        for(Type s : type){
                            if(s.getTypeName().equals(l.getTypeTarif().getTypeName())){
                                f = false;
                            }
                            if(f){
                                type.add(l.getTypeTarif());
                            }
                        }
                    }

                    /*
                    for(Type t : type){
                        for(Lombard l :lombards){
                            if(t.getTypeName().equals(l.getTypeTarif().getTypeName())){
                                l.setTypeTarif(t);
                            }
                        }
                    }

                    LOG.trace("======================================");
                    for (Lombard l : lombards){
                        LOG.trace("type == "+ l.getTypeTarif().hashCode());
                        LOG.trace("type == "+ l.getTypeTarif());
                    }
                    */

/////////////////////////

                    int size = lombards.size();
                    session.setAttribute("user", user);
                    session.setAttribute("lombards", lombards);
                    session.setAttribute("size", size);

                    List<String> listRegion = new ArrayList<String>();
                    for(Lombard l : lombards){
                        if(listRegion.isEmpty()){
                            listRegion.add(l.getRegion());
                            continue;
                        }
                        boolean b = true;
                        for(String s : listRegion){
                            if(s.equals(l.getRegion())){
                                b=false;
                            }
                        }
                        if(b){
                            listRegion.add(l.getRegion());
                        }

                    }
                    session.setAttribute("listRegion", listRegion);

                } catch (ClassNotFoundException e) {
                    LOG.trace("не удалось найти файл с тарифами");
                }

                flag = false;
                break;
            }
        }

        if(flag){
            throw new AppException("Cannot find user with such login/password");
        }

        LOG.debug("Command finished");
        return forward;
    }
}
