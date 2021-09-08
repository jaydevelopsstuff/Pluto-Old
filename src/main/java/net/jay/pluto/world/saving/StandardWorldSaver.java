package net.jay.pluto.world.saving;

import java.io.FileNotFoundException;

public class StandardWorldSaver extends AbstractWorldSaver {
    public StandardWorldSaver(String fullPath) throws FileNotFoundException {
        super(fullPath);
    }

    public StandardWorldSaver(String directory, String worldName) throws FileNotFoundException {
        super(directory, worldName);
    }

    @Override
    public void saveWorld() {

    }

    @Override
    protected void saveHeader() {

    }

    @Override
    protected void saveTiles() {

    }

    @Override
    protected void saveChests() {

    }

    @Override
    protected void saveSigns() {

    }

    @Override
    protected void saveDummies() {

    }

    @Override
    protected void saveNPCs() {

    }

    @Override
    protected void saveFooter() {

    }
}
