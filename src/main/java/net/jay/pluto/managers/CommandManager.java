package net.jay.pluto.managers;

import lombok.Getter;
import net.jay.pluto.data.StandardMessages;
import net.jay.pluto.entity.player.ManageablePlayer;
import net.jay.pluto.messaging.commands.Command;
import net.jay.pluto.messaging.commands.HelpCommand;
import net.jay.pluto.util.RawLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CommandManager implements Manager {
    private boolean running;
    @Getter
    private final List<Command> registeredCommands = new ArrayList<>();
    private final List<Command> commandQueue = new CopyOnWriteArrayList<>();

    public void initialize() {
        running = true;
        registeredCommands.add(new HelpCommand());
        new Thread(this::startCommandQueueExecutionThreaded, "Command Executor").start();
    }

    public void queueHandleCommand(String content) {
        String commandName = content.split(" ")[0];

        for(Command command : registeredCommands) {
            if(command.isSameName(commandName)) {
                command.ready(content);
                commandQueue.add(command);
                return;
            }
        }

        // Could not find given command
        RawLogger.print(StandardMessages.NoCommandFound);
    }

    public void queueHandleCommand(ManageablePlayer source, String content) {
        Command.Source realSource = new Command.Source(source);
        String commandName = content.split(" ")[0];

        for(Command command : registeredCommands) {
            if(command.isSameName(commandName)) {
                command.ready(realSource, content);
                commandQueue.add(command);
                return;
            }
        }

        // Could not find given command
        realSource.sendMessage(StandardMessages.NoCommandFound);
    }

    private void startCommandQueueExecutionThreaded() {
        while(running) {
            if(!commandQueue.isEmpty()) {
                int i = 0;
                for(Command queuedCommand : commandQueue) {
                    // Parse
                    queuedCommand.parse();
                    // Execute
                    queuedCommand.execute();

                    commandQueue.remove(i);
                    i++;
                }
            }
            try {
                Thread.sleep(1);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void shutdown() {
        running = false;
    }
}
