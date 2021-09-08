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
    public void loadWorld() {

    }

    @Override
    protected void loadHeader() {

    }

    @Override
    protected void loadTiles() {

    }

    @Override
    protected void loadChests() {

    }

    @Override
    protected void loadSigns() {

    }

    @Override
    protected void loadDummies() {

    }

    @Override
    protected void loadNPCs() {

    }

    @Override
    protected void loadFooter() {

    }


}
