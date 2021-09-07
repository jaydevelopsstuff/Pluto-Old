package net.jay.pluto.permissions;

import net.jay.pluto.groups.IntegratedGroups;

class PermissionBan extends Permission {
    public PermissionBan() {
        super("Ban", "pluto.moderation.ban", IntegratedGroups.OPERATOR);
    }
}
