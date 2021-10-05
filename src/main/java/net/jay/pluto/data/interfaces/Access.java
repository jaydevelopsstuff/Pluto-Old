package net.jay.pluto.data.interfaces;

import net.jay.pluto.PlutoServer;
import net.jay.pluto.world.World;
import org.apache.logging.log4j.Logger;

public interface Access {
    PlutoServer server = PlutoServer.getInstance();
    World world = server.getWorld();
    Logger mainLogger = server.getMainLogger();
}
