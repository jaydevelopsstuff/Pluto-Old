package net.jay.pluto.managers;

import net.jay.pluto.data.interfaces.Access;

import java.util.Scanner;

public class ConsoleManager implements Manager, Access {
    private boolean running;

    private Scanner input;

    private Thread listeningThread;

    public ConsoleManager() {
        running = true;
        initialize();
    }

    private void initialize() {
        input = new Scanner(System.in);

        new Thread(this::startConsoleListeningThreaded, "Console Reader").start();
    }

    private void startConsoleListeningThreaded() {
        while(running) {
            if(input.hasNextLine()) {
                String line = input.nextLine();
                server.getCommandManager().queueHandleCommand(line);
            }
            try {
                Thread.sleep(10);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void shutdown() {
        running = false;
        listeningThread.interrupt();
    }
}
