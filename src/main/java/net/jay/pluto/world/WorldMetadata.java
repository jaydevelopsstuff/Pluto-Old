package net.jay.pluto.world;

import net.jay.pluto.io.TerrariaReader;

import java.io.InputStream;
import java.time.LocalDateTime;

public class WorldMetadata {
    private boolean favorited;

    private int seed;
    private String seedText;

    private String worldGenVersion;

    private String name;

    private LocalDateTime creationTime;

    private WorldDifficulty difficulty;

    private boolean drunk;

    private WorldEvil evil;

    private WorldSize size;

    public WorldMetadata(int seed, String seedText, String worldGenVersion, String name, LocalDateTime creationTime, WorldDifficulty difficulty, boolean drunk, WorldEvil evil, WorldSize size) {
        this.seed = seed;
        this.seedText = seedText;
        this.worldGenVersion = worldGenVersion;
        this.name = name;
        this.creationTime = creationTime;
        this.difficulty = difficulty;
        this.drunk = drunk;
        this.evil = evil;
        this.size = size;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public String getSeedText() {
        return seedText;
    }

    public void setSeedText(String seedText) {
        this.seedText = seedText;
    }

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
