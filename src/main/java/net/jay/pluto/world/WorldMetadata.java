package net.jay.pluto.world;


import java.time.LocalDateTime;

public class WorldMetadata {
    private boolean favorited;
    private long revision;

    private LocalDateTime creationTime;

    public WorldMetadata(boolean favorited, long revision, LocalDateTime creationTime) {
        this.favorited = favorited;
        this.revision = revision;
        this.creationTime = creationTime;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public long getRevision() {
        return revision;
    }

    public void setRevision(long revision) {
        this.revision = revision;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
}
