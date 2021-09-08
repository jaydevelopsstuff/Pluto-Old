package net.jay.pluto.world.loading;

import net.jay.pluto.io.TerrariaReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class AbstractWorldLoader {
    protected final String worldPath;
    protected final File worldFile;
    protected final TerrariaReader reader;

    public AbstractWorldLoader(String fullPathToWorld) throws FileNotFoundException {
        this.worldPath = fullPathToWorld;
        this.worldFile = new File(worldPath);
        if(!worldFile.exists()) throw new FileNotFoundException();
        if(worldFile.isDirectory()) throw new IllegalStateException("World file cannot be a directory");
        this.reader = new TerrariaReader(new FileInputStream(worldFile));
    }

    public AbstractWorldLoader(String directory, String worldName) throws FileNotFoundException {
        this(directory + "/" + worldName + ".wld");
    }

    public abstract void saveWorld();

    protected abstract void saveHeader();

    protected abstract void saveTiles();

    protected abstract void saveChests();

    protected abstract void saveSigns();

    protected abstract void saveDummies();

    protected abstract void saveNPCs();

    protected abstract void saveFooter();
}
