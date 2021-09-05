package net.jay.pluto.io;

import java.io.File;

public interface IWorldLoader {
    void loadWorld(String worldFile);

    void loadWorld(File worldFile);

    void loadFormatHeader();

    void loadHeader();

    void loadTiles();

    void loadChests();

    void loadSigns();

    void loadDummies();

    void loadNPCs();

    void loadFooter();
}
