package net.jay.pluto.permissions;

import net.jay.pluto.groups.IntegratedGroups;

class PermissionPlutoAll extends Permission {
    public PermissionPlutoAll() {
        super("All Pluto Permissions", "pluto.all", new Permission[] {
                DefaultPermissions.Ban, DefaultPermissions.Kick
        }, IntegratedGroups.OPERATOR);
    }
}
