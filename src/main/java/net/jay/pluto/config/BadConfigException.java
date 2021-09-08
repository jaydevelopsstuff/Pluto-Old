package net.jay.pluto.config;

public class BadConfigException extends Exception {
    public BadConfigException(String reason) {
        super(reason);
    }
}
