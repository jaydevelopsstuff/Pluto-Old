package net.jay.pluto.world;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class WorldMetadata {
    private int versionNumber;

    private boolean favorited;
    private BigInteger revision;

    private String name;

    private LocalDateTime creationTime;

    public WorldMetadata(int versionNumber, boolean favorited, BigInteger revision, String name, LocalDateTime creationTime) {
        this.versionNumber = versionNumber;
        this.favorited = favorited;
        this.revision = revision;
        this.name = name;
        this.creationTime = creationTime;
    }

    public int getVersionNumber() {
        return versionNumber;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public BigInteger getRevision() {
        return revision;
    }

    public void setRevision(BigInteger revision) {
        this.revision = revision;
    }

    public void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
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
}
