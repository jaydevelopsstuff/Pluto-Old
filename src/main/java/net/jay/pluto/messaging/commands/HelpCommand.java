package net.jay.pluto.messaging.commands;

public class HelpCommand extends Command {
    private static final String response = "TBD";

    public HelpCommand() {
        super("pluto.help", "Help", "Gives information on Pluto");
    }

    @Override
    public boolean parse() {
        // Nothing to parse
        return true;
    }

    @Override
    public boolean execute() {
        source.sendMessage(response);
        return true;
    }
}
