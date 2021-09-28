package net.jay.pluto.world.saving;

import net.jay.pluto.world.WorldException;

public class WorldSavingException extends WorldException {
    public WorldSavingException(String message) {
        super(message);
    }

    public WorldSavingException(String message, Throwable cause) {
        super(message, cause);
    }
}
