package net.jay.pluto.messaging.commands;

import net.jay.pluto.messaging.Message;
import net.jay.pluto.permissions.Permission;

public interface Command {
    boolean parse(Message message);

    boolean execute();

    Permission[] getRequiredPermissions();
}
