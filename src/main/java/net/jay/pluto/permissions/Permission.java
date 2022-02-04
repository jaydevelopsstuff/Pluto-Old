package net.jay.pluto.permissions;

import lombok.Getter;
import net.jay.pluto.groups.IntegratedGroups;

/**
 * A class representing a permission, can be extended to create a new permission
 * @author Jay
 */
@Getter
public class Permission {
    /** The name of this permission */
    private final String name;
    /**
     * This permission's ID, which <strong>must be unique</strong>, usually following the format of domain.category.name
     * <br>
     * Example: pluto.moderation.ban
     */
    private final String ID;
    /** The permissions that this permission inherits */
    private final Permission[] childPermissions;
    /** The default group this permission falls into, null means none */
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
}
