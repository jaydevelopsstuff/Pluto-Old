package net.jay.pluto.io;

import java.io.File;

// TODO Finish rest of this
public interface IWorldSaver {
    void saveWorld(String saveTarget);

    void saveWorld(File saveTarget);

    void saveFormatHeader();
}
