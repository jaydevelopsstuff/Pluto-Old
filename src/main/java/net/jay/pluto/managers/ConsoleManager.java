package net.jay.pluto.managers;

import java.util.Scanner;

public class ConsoleManager implements Manager {
    private boolean running;

    private Scanner input;

    private Thread listeningThread;

    public ConsoleManager() {
        running = true;
        initialize();
    }

    private void initialize() {
        input = new Scanner(System.in);
    }

    private void startConsoleListeningThreaded() {
        while(running) {
            if(input.hasNextLine()) {
                String line = input.nextLine();

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
