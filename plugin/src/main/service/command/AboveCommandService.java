package src.main.service.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AboveCommandService implements CommandPlugin {
    private static  final Logger log = LoggerFactory.getLogger(JpsCommandService.class);

    @Override
    public boolean supports(String delimiter) {
        return  delimiter.equals("Above");
    }

    @Override
    public void run(String parameter) {
        log.info("param : {}" ,parameter);
    }
}
