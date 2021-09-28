package net.jay.pluto.world.saving;

import java.io.FileNotFoundException;

public class ReLogicWorldSaver extends AbstractWorldSaver {
    public ReLogicWorldSaver(String fullPath) throws FileNotFoundException {
        super(fullPath);
    }

    public ReLogicWorldSaver(String directory, String worldName) throws FileNotFoundException {
        super(directory, worldName);
    }

    @Override
    public void saveWorld() {

    }
}
