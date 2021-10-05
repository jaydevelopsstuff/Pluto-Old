package net.jay.pluto.util;

public class BitsByte {
    private final boolean[] bits;

    public BitsByte() {
        this.bits = new boolean[8];
    }

    public BitsByte(boolean[] bits) {
        if(bits.length != 8) throw new IllegalArgumentException("Bits array must be 8 in length");
        this.bits = bits;
    }

    public BitsByte(byte b) {
        bits = new boolean[] { getBit(b, 0), getBit(b, 1), getBit(b, 2), getBit(b, 3), getBit(b, 4), getBit(b, 5), getBit(b, 6), getBit(b, 7) };
    }

    public boolean getBit(int index) {
        if(checkIndex(index)) throw new IllegalArgumentException("Index out of bounds");
        return bits[index];
    }

    public void setBit(int index, boolean bit) {
        if(checkIndex(index)) throw new IllegalArgumentException("Index out of bounds");
        bits[index] = bit;
    }

    public void setBits(boolean... bits) {
        if(bits.length > 8) throw new IllegalArgumentException("Bits length cannot be more than 8");
        int index = 0;
        for(boolean bit : bits) {
            this.bits[index] = bit;
            index++;
        }
    }

    public byte toByte() {
        return build();
    }

    public byte build() {
        byte byteValue = 0;

        int index = 0;
        for(boolean bit : bits) {
            byteValue |= (bit ? 1 : 0) << index;
            index++;
        }

        return byteValue;
    }

    public static boolean getBit(byte b, int index) {
        if(checkIndex(index)) throw new IllegalArgumentException("Index out of bounds");
        return ((b >> index) & 1) == 1;
    }

    private static boolean checkIndex(int index) {
        return index < 0 || index > 7;
    }
}
