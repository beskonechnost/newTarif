package ua.fromTarif.controller.fromCommand;

import org.apache.log4j.Logger;
import ua.fromTarif.controller.command.EditTarifsAndPricesCommand;
import ua.fromTarif.controller.command.ForSomeCommand;
import ua.fromTarif.controller.command.LoginCommand;
import ua.fromTarif.controller.command.LombardTarifCommand;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Андрей on 18.05.2017.
 */
public class CommandContainer {

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        commands.put("login", new LoginCommand());
        commands.put("lombardTarif", new LombardTarifCommand());
        commands.put("forSome", new ForSomeCommand());
        commands.put("editTarifsAndPrices", new EditTarifsAndPricesCommand());

        LOG.debug("Command container was successfully initialized");
        LOG.trace("Number of commands --> " + commands.size());
    }

    /**
     * Returns command object with the given name.
     *
     * @param commandName
     *            Name of the command.
     * @return Command object.
     */
    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }

}
