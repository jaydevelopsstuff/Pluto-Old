package net.jay.pluto.data.holders;

import net.jay.pluto.data.enums.WorldDifficulty;
import net.jay.pluto.data.enums.WorldEvil;
import net.jay.pluto.data.enums.WorldSize;

import java.time.LocalDateTime;

public class WorldMetadata {
    private String worldGenVersion;

    private String name;

    private LocalDateTime creationTime;

    private WorldDifficulty difficulty;

    private boolean drunk;

    private WorldEvil evil;

    private WorldSize size;

    public String getWorldGenVersion() {
        return worldGenVersion;
    }

    public void setWorldGenVersion(String worldGenVersion) {
        this.worldGenVersion = worldGenVersion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public WorldDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(WorldDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isDrunk() {
        return drunk;
    }

    public void setDrunk(boolean drunk) {
        this.drunk = drunk;
    }

    public WorldEvil getEvil() {
        return evil;
    }

    public void setEvil(WorldEvil evil) {
        this.evil = evil;
    }

    public WorldSize getSize() {
        return size;
    }

    public void setSize(WorldSize size) {
        this.size = size;
    }

    public int getWorldWidth() {
        return size.width;
    }

    public int getWorldHeight() {
        return size.height;
    }
}
