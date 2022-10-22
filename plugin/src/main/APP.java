package src.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import src.main.service.command.*;

import java.util.Arrays;
import java.util.Optional;

public class APP {
    private static final Logger log = LoggerFactory.getLogger(APP.class);

    private static SimplePluginRegister<CommandPlugin,String> simplePluginRegister;

    static {
        simplePluginRegister = new SimplePluginRegister<CommandPlugin, String>(Arrays.asList(new JpsCommandService(),new MavenCommandClass()
        ,new AboveCommandService(),new ClassCommandPlugin()));

    }

    public static void main(String[] args) {
        if(args == null || args.length <= 0 ){
            log.info("There is no args, exit");
        }

        String value = null;
        String command = null;
        if(args.length > 1){
            value = args[1];
        }
        Optional<CommandPlugin> pluginFor = simplePluginRegister.getPluginFor(command);
        String value2 = value;
        pluginFor.ifPresentOrElse(cp -> {
            log.info("run",cp.getClass().getSimpleName());
            cp.run(value2);
        },new Thread(() -> log.info("wrong command")));
    }
}
