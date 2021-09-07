package net.jay.pluto.groups;

public enum IntegratedGroups {
    DEFAULT(new DefaultGroup()),
    OPERATOR(new OperatorGroup());

    public final Group group;

    IntegratedGroups(Group group) {
        this.group = group;
    }
}
