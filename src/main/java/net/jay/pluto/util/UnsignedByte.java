package net.jay.pluto.util;

public class UnsignedByte {
    private byte value;

    public UnsignedByte(byte value) {
        this.value = value;
    }

    public UnsignedByte(int value) {
        if(value < 0 || value > 255) throw new IllegalArgumentException("Value must be between 0 and 255");
        this.value = (byte)value;
    }

    public byte readRaw() {
        return value;
    }

    public int readReal() {
        return value & 0xFF;
    }

    public void setRaw(byte b) {
        value = b;
    }

    public void setReal(int i) {
        if(value < 0 || value > 255) throw new IllegalArgumentException("Value must be between 0 and 255");
        value = (byte)i;
    }

    public static short toShort(byte value) {
        return (byte)(toInt(value));
    }

    public static int toInt(byte value) {
        return value & 0xFF;
    }
}
