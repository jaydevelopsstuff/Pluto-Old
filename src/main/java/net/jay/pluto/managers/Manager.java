package net.jay.pluto.managers;

public interface Manager {
    void initialize();
    /** Shuts down the manager and all of its components */
    void shutdown();
}
