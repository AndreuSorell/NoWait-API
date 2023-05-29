package edu.poniperro.nowait.apps;

import edu.poniperro.nowait.apps.core.api.CoreApplication;
import edu.poniperro.nowait.shared.infraestructure.cli.ConsoleCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.HashMap;

public class Application {
    public static void main(String[] args) {
        System.out.println("test!!!");
        if (args.length < 2) {
            throw new RuntimeException("There are not enough arguments");
        }

        String applicationName = args[0];
        String commandName     = args[1];
        String portNumber      = (args.length >= 3) ? args[2] : "8080";
        boolean isApiCommand   = commandName.equals("api");
        boolean isConsume      = commandName.equals("domain-events:rabbitmq:consume");

        // ELIMINAR
        //  Se fuerza nombre de aplicación hasta que Joopbox cambie el comando para realizar deploy
        if(isConsume){
            applicationName = "core_consume";
        }

        ensureApplicationExist(applicationName);
        ensureCommandExist(applicationName, commandName, (isApiCommand || isConsume));

        Class<?> applicationClass = applications().get(applicationName);

        SpringApplication app = new SpringApplication(applicationClass);

        if (!isApiCommand && !isConsume) {
            app.setWebApplicationType(WebApplicationType.NONE);
        }

        System.setProperty("server.port", portNumber);

        ConfigurableApplicationContext context = app.run(args);

        if (!isApiCommand) {
            ConsoleCommand command = (ConsoleCommand) context.getBean(
                    commands().get(applicationName).get(commandName)
            );

            command.execute(Arrays.copyOfRange(args, 2, args.length));
        }
    }

    private static void ensureApplicationExist(String applicationName) {
        if (!applications().containsKey(applicationName)) {
            throw new RuntimeException(String.format(
                    "The application <%s> doesn't exist. Valids:\n- %s",
                    applicationName,
                    String.join("\n- ", applications().keySet())
            ));
        }
    }

    private static void ensureCommandExist(String applicationName, String commandName, boolean isWebCommand) {
        if (!isWebCommand && !existCommand(applicationName, commandName)) {
            throw new RuntimeException(String.format(
                    "The command <%s> for application <%s> doesn't exist. Valids (application.command):\n- api\n- %s",
                    commandName,
                    applicationName,
                    String.join("\n- ", commands().get(applicationName).keySet())
            ));
        }
    }

    private static HashMap<String, Class<?>> applications() {
        HashMap<String, Class<?>> applications = new HashMap<>();

        applications.put("core_api", CoreApplication.class);
        //applications.put("core_consume", ConsumeApplication.class);

        return applications;
    }

    private static HashMap<String, HashMap<String, Class<?>>> commands() {
        HashMap<String, HashMap<String, Class<?>>> commands = new HashMap<>();

        commands.put("core_api", CoreApplication.commands());
        //commands.put("core_consume", ConsumeApplication.commands());

        return commands;
    }

    private static Boolean existCommand(String applicatinName, String commandName) {
        HashMap<String, HashMap<String, Class<?>>> commands = commands();

        return commands.containsKey(applicatinName) && commands.get(applicatinName).containsKey(commandName);
    }
}
