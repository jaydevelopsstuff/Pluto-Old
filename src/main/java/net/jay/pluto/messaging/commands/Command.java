package net.jay.pluto.messaging.commands;

import lombok.Getter;
import net.jay.pluto.entity.player.ManageablePlayer;
import net.jay.pluto.permissions.Permission;
import net.jay.pluto.util.RawLogger;

import java.util.Arrays;

public abstract class Command {
    @Getter
    protected final String ID;
    @Getter
    protected final String name;
    @Getter
    protected final String description;
    @Getter
    protected final Permission[] requiredPermissions;

    protected Source source;
    protected String rawContent;
    protected String afterText;
    protected String[] args;

    public Command(String ID, String name, String description) {
        this.ID = "commands." + ID;
        this.name = name;
        this.description = description;
        this.requiredPermissions = null;
    }

    public Command(String ID, String name, String description, Permission[] requiredPermissions) {
        this.ID = "commands." + ID;
        this.name = name;
        this.description = description;
        this.requiredPermissions = requiredPermissions;
    }

    public void ready(String content) {
        this.source = new Source();
        this.rawContent = content;
        String[] tempArgs = this.rawContent.split(" ");
        int index = this.rawContent.indexOf(" ");
        this.afterText = index == -1 ? null : this.rawContent.substring(index);
        this.args = Arrays.stream(tempArgs).skip(1).toArray(String[]::new);
    }

    public void ready(Source source, String content) {
        this.source = source;
        this.rawContent = content;
        String[] tempArgs = this.rawContent.split(" ");
        this.afterText = this.rawContent.substring(this.rawContent.indexOf(" "));
        this.args = (String[])Arrays.stream(tempArgs).skip(1).toArray();
    }

    public abstract boolean parse();

    public abstract boolean execute();

    /**
     * Checks if a given name is the same as this command's name, ignores case.
     * @param name A name
     * @return Whether the name of this command is the same as the one provided
     */
    public boolean isSameName(String name) {
        return this.name.equalsIgnoreCase(name);
    }

    public boolean is(Command command) {
        return ID.equals(command.getID());
    }

    public static class Source {
        private final Type type;
        private final ManageablePlayer playerSource;

        public Source() {
            this.type = Type.CONSOLE;
            this.playerSource = null;
        }

        public Source(ManageablePlayer playerSource) {
            this.type = Type.PLAYER;
            this.playerSource = playerSource;
        }

        public void sendMessage(String message) {
            if(type == Type.CONSOLE) RawLogger.print(message);
            // else playerSource.sendMessage(message);
        }

        public Type getType() {
            return type;
        }

        public ManageablePlayer getPlayerSource() {
            if(type == Type.CONSOLE) throw new IllegalStateException("Cannot get player source when source is from console");
            return playerSource;
        }

        public enum Type {
            CONSOLE,
            PLAYER
        }
    }
}
