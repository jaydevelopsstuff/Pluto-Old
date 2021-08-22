package net.jay.pluto;

import net.jay.pluto.net.NetManager;

import java.io.IOException;

public class PlutoServer {
    private NetManager netManager;

    public void start() throws IOException {
        netManager = new NetManager();
        netManager.startServer();
    }
}
