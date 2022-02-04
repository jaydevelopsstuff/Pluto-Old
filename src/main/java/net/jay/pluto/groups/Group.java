package net.jay.pluto.groups;

import net.jay.pluto.permissions.Permission;

import java.util.Arrays;
import java.util.List;

public class Group {
    private final Permission[] defaultPermissions;
    private final List<Permission> permissions;

    public Group(Permission[] defaultPermissions) {
        this.defaultPermissions = defaultPermissions;
        this.permissions = List.of(defaultPermissions);
    }

    public boolean hasPermission(Permission permission) {
        for(Permission listPermission : permissions) {
            if(listPermission.equals(permission)) return true;
        }
        return false;
    }

    public Permission[] getPermissions() {
        return permissions.toArray(new Permission[0]);
    }
}
