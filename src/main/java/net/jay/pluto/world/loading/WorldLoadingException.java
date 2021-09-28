package net.jay.pluto.world.loading;

import net.jay.pluto.world.WorldException;

public class WorldLoadingException extends WorldException {
    public WorldLoadingException(String message) {
        super(message);
    }

    public WorldLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
