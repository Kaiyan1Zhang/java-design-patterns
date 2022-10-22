package src.main.service.command;

import src.main.Plugin;

public interface CommandPlugin extends Plugin<String> {
    void run(String parameter);
}

