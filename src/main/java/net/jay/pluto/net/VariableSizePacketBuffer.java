package net.jay.pluto.net;

import net.jay.pluto.util.TColor;
import org.apache.commons.collections.primitives.ArrayByteList;
import org.apache.commons.collections.primitives.ByteList;

import java.nio.charset.StandardCharsets;

public class VariableSizePacketBuffer {
    private final ByteList buffer;

    private int readerIndex = 0;

    public VariableSizePacketBuffer() {
        this.buffer = new ArrayByteList();
    }

    public byte[] getBuffer() {
        return buffer.toArray();
    }

    public int getAllocation() {
        return buffer.size();
    }

    public void resetReaderIndex() {
        readerIndex = 0;
    }

    public PacketBuffer toUnreadOnly() {
        byte[] unreadBytes = new byte[getAllocation() - readerIndex];

        if(getAllocation() - readerIndex >= 0) System.arraycopy(buffer.toArray(), readerIndex, unreadBytes, 0, getAllocation() - readerIndex);

        return new PacketBuffer(unreadBytes);
    }


    // READ


    public short readUnsignedByte() {
        short result = getUnsignedByte(readerIndex);
        readerIndex++;
        return result;
    }

    public boolean readBoolean() {
        boolean result = getBoolean(readerIndex);
        readerIndex++;
        return result;
    }

    public byte readByte() {
        byte result = getByte(readerIndex);
        readerIndex++;
        return result;
    }

    public short readShort() {
        short result = getShort(readerIndex);
        readerIndex += 2;
        return result;
    }

    public int readInt() {
        int result = getInt(readerIndex);
        readerIndex += 4;
        return result;
    }

    public long readLong() {
        long result = getLong(readerIndex);
        readerIndex += 8;
        return result;
    }

    public float readFloat() {
        float result = getFloat(readerIndex);
        readerIndex += 4;
        return result;
    }

    public double readDouble() {
        double result = getDouble(readerIndex);
        readerIndex += 8;
        return result;
    }

    public String readString() {
        StringAndTwoIntegers result = getStringS(readerIndex);
        readerIndex += result.int1 + result.int2;
        return result.string;
    }

    public String readString(int numBytes) {
        String result = getString(readerIndex, numBytes);
        readerIndex += numBytes;
        return result;
    }

    public TColor readColor() {
        TColor result = getColor(readerIndex);
        readerIndex += 3;
        return result;
    }

    public short getUnsignedByte(int index) {
        return (short)(getByte(index) & 0xFF);
    }

    public boolean getBoolean(int index) {
        if(index < 0 || isntInBounds(index, 1)) throw new ArrayIndexOutOfBoundsException();

        return getUnsignedByte(index) != 0;
    }

    public byte getByte(int index) {
        if(index < 0 || isntInBounds(index, 1)) throw new ArrayIndexOutOfBoundsException();

        return buffer.get(index);
    }

    public short getShort(int startIndex) {
        if(startIndex < 0 || isntInBounds(startIndex, 2)) throw new ArrayIndexOutOfBoundsException();

        short b1 = getUnsignedByte(startIndex);
        short b2 = getUnsignedByte(startIndex + 1);

        return (short)(b1 | (b2 << 8));
    }

    public int getInt(int startIndex) {
        if(startIndex < 0 || isntInBounds(startIndex, 4)) throw new ArrayIndexOutOfBoundsException();

        short b1 = getUnsignedByte(startIndex);
        short b2 = getUnsignedByte(startIndex + 1);
        short b3 = getUnsignedByte(startIndex + 2);
        short b4 = getUnsignedByte(startIndex + 3);

        return (b1 | (b2 << 8) | (b3 << 16) | (b4 << 24));
    }

    public long getLong(int startIndex) {
        if(startIndex < 0 || isntInBounds(startIndex, 8)) throw new ArrayIndexOutOfBoundsException();

        // Pretty ugly but oh well, might improve later
        // Needs to be updated, this may very well be broken
        short b1 = getUnsignedByte(startIndex);
        short b2 = getUnsignedByte(startIndex + 1);
        short b3 = getUnsignedByte(startIndex + 2);
        short b4 = getUnsignedByte(startIndex + 3);
        short b5 = getUnsignedByte(startIndex + 4);
        short b6 = getUnsignedByte(startIndex + 5);
        short b7 = getUnsignedByte(startIndex + 6);
        short b8 = getUnsignedByte(startIndex + 7);

        int num = (b1 | (b2 << 8) | (b3 << 16) | (b4 << 24));
        int num2 = (b5 | (b6 << 8) | (b7 << 16) | (b8 << 24));
        return (num | ((long)num2 << 32));
    }

    public float getFloat(int startIndex) {
        return Float.intBitsToFloat(getInt(startIndex));
    }

    public double getDouble(int startIndex) {
        return Double.longBitsToDouble(getLong(startIndex));
    }

    public String getString(int startIndex) {
        if(startIndex < 0 || isntInBounds(startIndex, 1)) throw new ArrayIndexOutOfBoundsException();

        int numBytes = get7BitEncodedInt(startIndex).int1;

        if(isntInBounds(startIndex + 1, numBytes)) throw new ArrayIndexOutOfBoundsException();

        return new String(buffer.toArray(), startIndex + 1, numBytes, StandardCharsets.UTF_8);
    }

    public String getString(int startIndex, int numBytes) {
        if(startIndex < 0 || isntInBounds(startIndex, numBytes)) throw new ArrayIndexOutOfBoundsException();

        return new String(buffer.toArray(), startIndex, numBytes, StandardCharsets.UTF_8);
    }

    // Necessary private function, shouldn't be used normally
    private StringAndTwoIntegers getStringS(int startIndex) {
        if(startIndex < 0 || isntInBounds(startIndex, 1)) throw new ArrayIndexOutOfBoundsException();

        TwoIntegers result = get7BitEncodedInt(startIndex);
        int numBytes = result.int1;

        if(isntInBounds(startIndex + 1, numBytes)) throw new ArrayIndexOutOfBoundsException();

        return new StringAndTwoIntegers(new String(buffer.toArray(), startIndex + 1, numBytes, StandardCharsets.UTF_8), numBytes, result.int2);
    }

    public TColor getColor(int startIndex) {
        if(startIndex < 0 || isntInBounds(startIndex, 3)) throw new ArrayIndexOutOfBoundsException();

        byte red = getByte(startIndex);
        byte green = getByte(startIndex + 1);
        byte blue = getByte(startIndex + 2);

        return new TColor(red, green, blue);
    }


    // WRITE


    /** Writes an unsigned byte */
    public void writeUnsignedByte(short s) {
        if(s < 0 || s > 255) throw new IllegalArgumentException("Short must be between 0 and 255");
        writeByte((byte)s);
    }

    public void writeBoolean(boolean b) {
        writeByte((byte)(b ? 1 : 0));
    }

    public void writeByte(byte b) {
        buffer.add(b);
    }

    public void writeBytes(byte[] bytes) {
        for(byte b : bytes) {
            buffer.add(b);
        }
    }

    public void writeShort(short s) {
        writeByte((byte)(s));
        writeByte((byte)(s >>> 8));
    }

    public void writeInt(int i) {
        writeByte((byte)(i));
        writeByte((byte)(i >>> 8));
        writeByte((byte)(i >>> 16));
        writeByte((byte)(i >>> 24));
    }

    public void writeLong(long l) {
        writeByte((byte)(l));
        writeByte((byte)(l >>> 8));
        writeByte((byte)(l >>> 16));
        writeByte((byte)(l >>> 24));
        writeByte((byte)(l >>> 32));
        writeByte((byte)(l >>> 40));
        writeByte((byte)(l >>> 48));
        writeByte((byte)(l >>> 56));
    }

    public void writeFloat(float f) {
        writeInt(Float.floatToIntBits(f));
    }

    public void writeDouble(double d) {
        writeLong(Double.doubleToLongBits(d));
    }

    public void writeString(String s) {
        byte[] stringBytes = s.getBytes(StandardCharsets.UTF_8);

        write7BitEncodedInt(stringBytes.length);
        writeBytes(s.getBytes(StandardCharsets.UTF_8));
    }

    public void writeBoolean(boolean b, int index) {
        if(index < 0 || isntInBounds(index, 1)) throw new ArrayIndexOutOfBoundsException();

        buffer.set(index, (byte)(b ? 1 : 0));
    }

    public void writeByte(byte b, int index) {
        if(index < 0 || isntInBounds(index, 1)) throw new ArrayIndexOutOfBoundsException();

        buffer.set(index, b);
    }

    public void writeBytes(byte[] b, int startIndex) {
        int byteArrayLength = b.length;
        if(startIndex < 0 || isntInBounds(startIndex, byteArrayLength)) throw new ArrayIndexOutOfBoundsException();

        int i = 0;
        for(byte bytee : b) {
            buffer.set(startIndex + i, bytee);
            i++;
        }
    }

    public void writeShort(short s, int startIndex) {
        if(startIndex < 0 || isntInBounds(startIndex, 2)) throw new ArrayIndexOutOfBoundsException();

        buffer.set(startIndex, (byte)(s));
        buffer.set(startIndex + 1, (byte)(s >>> 8));
    }

    public void writeInt(int i, int startIndex) {
        if(startIndex < 0 || isntInBounds(startIndex, 4)) throw new ArrayIndexOutOfBoundsException();

        buffer.set(startIndex, (byte)(i));
        buffer.set(startIndex + 1, (byte)(i >>> 8));
        buffer.set(startIndex + 2, (byte)(i >>> 16));
        buffer.set(startIndex + 3, (byte)(i >>> 24));
    }

    public void writeLong(long l, int startIndex) {
        if(startIndex < 0 || isntInBounds(startIndex, 4)) throw new ArrayIndexOutOfBoundsException();

        buffer.set(startIndex, (byte)(l));
        buffer.set(startIndex + 1, (byte)(l >>> 8));
        buffer.set(startIndex + 2, (byte)(l >>> 16));
        buffer.set(startIndex + 3, (byte)(l >>> 24));
        buffer.set(startIndex + 4, (byte)(l >>> 32));
        buffer.set(startIndex + 5, (byte)(l >>> 40));
        buffer.set(startIndex + 6, (byte)(l >>> 48));
        buffer.set(startIndex + 7, (byte)(l >>> 56));
    }

    public void writeFloat(float f, int startIndex) {
        writeInt(Float.floatToIntBits(f), startIndex);
    }

    public void writeDouble(double d, int startIndex) {
        writeLong(Double.doubleToLongBits(d), startIndex);
    }

    public int writeString(String s, int startIndex) {
        byte[] stringBytes = s.getBytes(StandardCharsets.UTF_8);
        int numBytes = stringBytes.length;

        if(isntInBounds(startIndex + 1, numBytes)) throw new ArrayIndexOutOfBoundsException();

        write7BitEncodedInt(numBytes, startIndex);
        writeBytes(s.getBytes(StandardCharsets.UTF_8), startIndex + 1);

        return numBytes;
    }

    private int writeStringS(String s, int startIndex) {
        byte[] stringBytes = s.getBytes(StandardCharsets.UTF_8);
        int numBytes = stringBytes.length;

        int addonBytes = write7BitEncodedInt(numBytes, startIndex);
        writeBytes(s.getBytes(StandardCharsets.UTF_8), startIndex + 1);

        return numBytes + addonBytes;
    }

    public void writeColor(TColor color) {
        writeByte((byte)color.getRed());
        writeByte((byte)color.getGreen());
        writeByte((byte)color.getBlue());
    }



    // Ported from C#'s BinaryReader class
    private TwoIntegers get7BitEncodedInt(int startIndex) {
        int num = 0;
        int num2 = 0;
        int runs = 0;
        byte b;
        do {
            if(num2 == 35) throw new IllegalStateException();
            b = getByte(startIndex + runs);
            num |= (b & 0x7F) << num2;
            num2 += 7;
            runs++;
        } while((b & 0x80) != 0);
        return new TwoIntegers(num, runs);
    }

    private int write7BitEncodedInt(int i) {
        int num;
        int j = 0;
        for (num = i; num >= 128; num >>= 7) {
            writeByte((byte)(num | 0x80));
            j++;
        }
        writeByte((byte)num);
        j++;
        return j;
    }

    private int write7BitEncodedInt(int i, int startIndex) {
        int num;
        int j = 0;
        for (num = i; num >= 128; num >>= 7)
        {
            writeByte((byte)(num | 0x80), startIndex + j);
            j++;
        }
        writeByte((byte)num, startIndex + j);
        j++;
        return j;
    }

    public boolean isntInBounds(int startIndex, int length) {
        for(int i = 0; i < length; i++) {
            if(startIndex + i > buffer.size() - 1) return true;
        }
        return false;
    }

    public PacketBuffer toNormal() {
        return new PacketBuffer(buffer.toArray());
    }

    public PacketBuffer toReadOnly() {
        return new PacketBuffer(buffer.toArray(), false);
    }

    private record TwoIntegers(int int1, int int2) {}
    private record StringAndTwoIntegers(String string, int int1, int int2) {}
}
