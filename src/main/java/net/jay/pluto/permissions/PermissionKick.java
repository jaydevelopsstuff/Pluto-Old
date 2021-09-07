package net.jay.pluto.permissions;

import net.jay.pluto.groups.IntegratedGroups;

class PermissionKick extends Permission {
    public PermissionKick() {
        super("Kick", "pluto.moderation.kick", IntegratedGroups.OPERATOR);
    }
}
