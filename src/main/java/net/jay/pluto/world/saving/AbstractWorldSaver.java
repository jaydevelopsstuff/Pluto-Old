package net.jay.pluto.world.saving;

import net.jay.pluto.data.interfaces.Access;
import net.jay.pluto.io.TerrariaWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public abstract class AbstractWorldSaver implements Access {
    protected final String worldSavePath;
    protected final File worldFile;
    protected final TerrariaWriter writer;

    public AbstractWorldSaver(String fullPath) throws FileNotFoundException {
        this.worldSavePath = fullPath;
        this.worldFile = new File(worldSavePath);
        if(worldFile.isDirectory()) throw new IllegalStateException("World file cannot be a directory");
        if(worldFile.exists()) server.getFileManager().clear(worldFile);
        this.writer = new TerrariaWriter(new FileOutputStream(worldFile));
    }

    public AbstractWorldSaver(String directory, String worldName) throws FileNotFoundException {
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
