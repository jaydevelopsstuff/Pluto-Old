package net.jay.pluto.entity.tileentity;

public class LogicSensor extends TileEntity {
    private byte logicCheck;
    private boolean on;

    public LogicSensor(int ID, short x, short y, byte logicCheck, boolean on) {
        super(ID, x, y);
        this.logicCheck = logicCheck;
        this.on = on;
    }

    public byte getLogicCheck() {
        return logicCheck;
    }

    public void setLogicCheck(byte logicCheck) {
        this.logicCheck = logicCheck;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }
}
