package net.jay.pluto.permissions;

import net.jay.pluto.groups.IntegratedGroups;

public class Permission {
    private final String name;
    private final String ID;
    private final Permission[] childPermissions;
    private final IntegratedGroups defaultPermissionGroup;

    public Permission(String name, String ID, IntegratedGroups defaultPermissionGroup) {
        this.name = name;
        this.ID = "permissions." + ID;
        this.childPermissions = new Permission[0];
        this.defaultPermissionGroup = defaultPermissionGroup;
    }

    public Permission(String name, String ID, Permission[] childPermissions, IntegratedGroups defaultPermissionGroup) {
        this.name = name;
        this.ID = "permissions." + ID;
        this.childPermissions = childPermissions;
        this.defaultPermissionGroup = defaultPermissionGroup;
    }

    public boolean equals(Permission permission) {
        return ID.equals(permission.getID());
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public Permission[] getChildPermissions() {
        return childPermissions;
    }

    public IntegratedGroups getDefaultPermissionGroup() {
        return defaultPermissionGroup;
    }
}
