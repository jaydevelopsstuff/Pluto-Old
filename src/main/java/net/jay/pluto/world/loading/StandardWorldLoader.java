package net.jay.pluto.world.loading;

import java.io.FileNotFoundException;

public class StandardWorldLoader extends AbstractWorldLoader {
    public StandardWorldLoader(String fullPathToWorld) throws FileNotFoundException {
        super(fullPathToWorld);
    }

    public StandardWorldLoader(String directory, String worldName) throws FileNotFoundException {
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
