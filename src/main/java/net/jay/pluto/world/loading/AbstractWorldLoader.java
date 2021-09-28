package net.jay.pluto.world.loading;

import net.jay.pluto.io.TerrariaReader;
import net.jay.pluto.world.World;
import net.jay.pluto.world.WorldMetadata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class AbstractWorldLoader {
    protected final String worldPath;
    protected final File worldFile;
    protected final FileInputStream inputStream;
    protected final TerrariaReader reader;

    public AbstractWorldLoader(String fullPathToWorld) throws FileNotFoundException {
        this.worldPath = fullPathToWorld;
        this.worldFile = new File(worldPath);
        if(!worldFile.exists()) throw new FileNotFoundException();
        if(worldFile.isDirectory()) throw new IllegalStateException("World file cannot be a directory");
        this.inputStream = new FileInputStream(worldFile);
        this.reader = new TerrariaReader(inputStream);
    }

    public AbstractWorldLoader(String directory, String worldName) throws FileNotFoundException {
        this(directory + "/" + worldName + ".wld");
    }

    public abstract void loadWorld() throws WorldLoadingException, IOException;

    public abstract World build();
}
