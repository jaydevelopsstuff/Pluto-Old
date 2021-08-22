package net.jay.pluto;

import net.jay.pluto.net.TCPServerManager;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            new TCPServerManager(7777);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
