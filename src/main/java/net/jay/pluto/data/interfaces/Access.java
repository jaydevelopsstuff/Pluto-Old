package net.jay.pluto.data.interfaces;

import net.jay.pluto.PlutoServer;
import org.apache.logging.log4j.Logger;

public interface Access {
    PlutoServer server = PlutoServer.getInstance();
    Logger mainLogger = server.getMainLogger();
}
