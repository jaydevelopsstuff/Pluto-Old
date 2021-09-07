package net.jay.pluto.groups;

import net.jay.pluto.permissions.DefaultPermissions;
import net.jay.pluto.permissions.Permission;

class OperatorGroup extends Group {
    public OperatorGroup() {
        super(new Permission[] { DefaultPermissions.All });
    }
}
