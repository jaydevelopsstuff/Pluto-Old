package net.jay.pluto.net;

import net.jay.pluto.util.TColor;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class PacketBuffer {
    private byte[] buffer;
    private boolean canWrite;

    private int readerIndex = 0;
    private int writerIndex = 0;

    public PacketBuffer(int allocation) {
        this.buffer = new byte[allocation];
        Arrays.fill(buffer, (byte)-128);
        canWrite = true;
    }

    public PacketBuffer(byte[] buffer) {
        this.buffer = buffer;
        this.canWrite = true;
    }

    public PacketBuffer(byte[] buffer, boolean canWrite) {
        this.buffer = buffer;
        this.canWrite = canWrite;
    }

    public byte[] getBuffer() {
        return buffer;
    }

    public int getAllocation() {
        return buffer.length;
    }

    public void resetIndexes() {
        resetReaderIndex();
        resetWriterIndex();
    }

    public void resetReaderIndex() {
        readerIndex = 0;
    }

    public void resetWriterIndex() {
        writerIndex = 0;
    }


    // READ


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

    public short readUnsignedByte() {
        return (short)readUnsignedByteInt();
    }

    public int readUnsignedByteInt() {
        return readByte() & 0xFF;
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

    public boolean getBoolean(int index) {
        if(index < 0 || isntInBounds(index, 1)) throw new ArrayIndexOutOfBoundsException();

        return buffer[index] != 0;
    }

    public byte getByte(int index) {
        if(index < 0 || isntInBounds(index, 1)) throw new ArrayIndexOutOfBoundsException();

        return buffer[index];
    }

    public short getShort(int startIndex) {
        if(startIndex < 0 || isntInBounds(startIndex, 2)) throw new ArrayIndexOutOfBoundsException();

        byte ch1 = buffer[startIndex];
        byte ch2 = buffer[startIndex + 1];

        return (short)((ch1) + (ch2 << 8));
    }

    public int getInt(int startIndex) {
        if(startIndex < 0 || isntInBounds(startIndex, 4)) throw new ArrayIndexOutOfBoundsException();

        byte ch1 = buffer[startIndex];
        byte ch2 = buffer[startIndex + 1];
        byte ch3 = buffer[startIndex + 2];
        byte ch4 = buffer[startIndex + 3];

        return ((ch1) + (ch2 << 8) + (ch3 << 16) + (ch4 << 24));
    }

    public long getLong(int startIndex) {
        if(startIndex < 0 || isntInBounds(startIndex, 8)) throw new ArrayIndexOutOfBoundsException();

        // Pretty ugly but oh well, might improve later
        byte ch1 = buffer[startIndex];
        byte ch2 = buffer[startIndex + 1];
        byte ch3 = buffer[startIndex + 2];
        byte ch4 = buffer[startIndex + 3];
        byte ch5 = buffer[startIndex + 4];
        byte ch6 = buffer[startIndex + 5];
        byte ch7 = buffer[startIndex + 6];
        byte ch8 = buffer[startIndex + 7];

        int num = (ch1| (ch2 << 8) | (ch3 << 16) | (ch4 << 24));
        int num2 = (ch5 | (ch6 << 8) | (ch7 << 16) | (ch8 << 24));
        return (((long)num2 << 32) | num);
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

        return new String(buffer, startIndex + 1, numBytes, StandardCharsets.UTF_8);
    }

    public String getString(int startIndex, int numBytes) {
        if(startIndex < 0 || isntInBounds(startIndex, numBytes)) throw new ArrayIndexOutOfBoundsException();

        return new String(buffer, startIndex, numBytes, StandardCharsets.UTF_8);
    }

    // Necessary private function, shouldn't be used normally
    private StringAndTwoIntegers getStringS(int startIndex) {
        if(startIndex < 0 || isntInBounds(startIndex, 1)) throw new ArrayIndexOutOfBoundsException();

        TwoIntegers result = get7BitEncodedInt(startIndex);
        int numBytes = result.int1;

        if(isntInBounds(startIndex + 1, numBytes)) throw new ArrayIndexOutOfBoundsException();

        return new StringAndTwoIntegers(new String(buffer, startIndex + 1, numBytes, StandardCharsets.UTF_8), numBytes, result.int2);
    }

    public TColor getColor(int startIndex) {
        if(startIndex < 0 || isntInBounds(startIndex, 3)) throw new ArrayIndexOutOfBoundsException();

        byte red = getByte(startIndex);
        byte green = getByte(startIndex + 1);
        byte blue = getByte(startIndex + 2);

        return new TColor(red, green, blue);
    }


    // WRITE


    public void writeBoolean(boolean b) {
        writeBoolean(b, writerIndex);
        writerIndex++;
    }

    public void writeByte(byte b) {
        writeByte(b, writerIndex);
        writerIndex++;
    }

    public void writeByte(short s) {
        if(s < -128 || s > 127) throw new IllegalArgumentException("Short must be between -128 and 127");
        writeByte((byte)s);
    }

    public void writeShort(short s) {
        writeShort(s, writerIndex);
        writerIndex += 2;
    }

    public void writeInt(int i) {
        writeInt(i, writerIndex);
        writerIndex += 4;
    }

    public void writeLong(long l) {
        writeLong(l, writerIndex);
        writerIndex += 8;
    }

    public void writeFloat(float f) {
        writeFloat(f, writerIndex);
        writerIndex += 4;
    }

    public void writeDouble(double d) {
        writeDouble(d, writerIndex);
        writerIndex += 8;
    }

    public void writeString(String s) {
        int writtenBytes = writeStringS(s, writerIndex);
        writerIndex += writtenBytes;
    }

    public void writeBoolean(boolean b, int index) {
        if(!canWrite) throw new IllegalStateException();
        if(index < 0 || isntInBounds(index, 1)) throw new ArrayIndexOutOfBoundsException();

        buffer[index] = (byte)(b ? 1 : 0);
    }

    public void writeByte(byte b, int index) {
        if(!canWrite) throw new IllegalStateException();
        if(index < 0 || isntInBounds(index, 1)) throw new ArrayIndexOutOfBoundsException();

        buffer[index] = b;
    }

    public void writeBytes(byte[] b, int startIndex) {
        if(!canWrite) throw new IllegalStateException();
        int byteArrayLength = b.length;
        if(startIndex < 0 || isntInBounds(startIndex, byteArrayLength)) throw new ArrayIndexOutOfBoundsException();

        int i = 0;
        for(byte bytee : b) {
            buffer[startIndex + i] = bytee;
            i++;
        }
    }

    public void writeShort(short s, int startIndex) {
        if(!canWrite) throw new IllegalStateException();
        if(startIndex < 0 || isntInBounds(startIndex, 2)) throw new ArrayIndexOutOfBoundsException();

        buffer[startIndex] = (byte)(s);
        buffer[startIndex + 1] = (byte)(s >>> 8);
    }

    public void writeInt(int i, int startIndex) {
        if(!canWrite) throw new IllegalStateException();
        if(startIndex < 0 || isntInBounds(startIndex, 4)) throw new ArrayIndexOutOfBoundsException();

        buffer[startIndex] = (byte)(i);
        buffer[startIndex + 1] = (byte)(i >>> 8);
        buffer[startIndex + 2] = (byte)(i >>> 16);
        buffer[startIndex + 3] = (byte)(i >>> 24);
    }

    public void writeLong(long l, int startIndex) {
        if(!canWrite) throw new IllegalStateException();
        if(startIndex < 0 || isntInBounds(startIndex, 4)) throw new ArrayIndexOutOfBoundsException();

        buffer[startIndex] = (byte)(l);
        buffer[startIndex + 1] = (byte)(l >>> 8);
        buffer[startIndex + 2] = (byte)(l >>> 16);
        buffer[startIndex + 3] = (byte)(l >>> 24);
        buffer[startIndex + 4] = (byte)(l >>> 32);
        buffer[startIndex + 5] = (byte)(l >>> 40);
        buffer[startIndex + 6] = (byte)(l >>> 48);
        buffer[startIndex + 7] = (byte)(l >>> 56);
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
            if(startIndex + i > buffer.length - 1) return true;
        }
        return false;
    }

    private record TwoIntegers(int int1, int int2) {}
    private record ThreeIntegers(int int1, int int2, int int3) {}
    private record StringAndTwoIntegers(String string, int int1, int int2) {}
}
