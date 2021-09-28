package net.jay.pluto.entity.tileentity;

public class TrainingDummy extends TileEntity {
    private short npc;

    public TrainingDummy(int ID, short x, short y, short npc) {
        super(ID, x, y);
        this.npc = npc;
    }
}
