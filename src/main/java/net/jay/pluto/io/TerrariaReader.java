package net.jay.pluto.io;

import net.jay.pluto.util.TColor;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class TerrariaReader extends InputStream {
    private static final Charset encoding = StandardCharsets.UTF_8;

    private final InputStream in;

    private long position = 0;

    public TerrariaReader(InputStream in) {
        this.in = in;
    }

    @Override
    public int available() throws IOException {
        return in.available();
    }

    @Override
    public int read() throws IOException {
        position++;
        return in.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        int bytesRead = in.read(b);
        position += bytesRead;
        return bytesRead;
    }

    public short readUnsignedByte() throws IOException {
        return (short)read();
    }

    public boolean readBoolean() throws IOException {
        return readByte() != 0;
    }

    public byte readByte() throws IOException {
        return (byte)readUnsignedByte();
    }

    public short readShort() throws IOException {
        int ch1 = readUnsignedByte();
        int ch2 = readUnsignedByte();
        if ((ch1 | ch2) < 0)
            throw new EOFException();
        return (short)((ch1) + (ch2 << 8));
    }

    public int readInt() throws IOException {
        int ch1 = readUnsignedByte();
        int ch2 = readUnsignedByte();
        int ch3 = readUnsignedByte();
        int ch4 = readUnsignedByte();
        if ((ch1 | ch2 | ch3 | ch4) < 0)
            throw new EOFException();
        return ((ch1) + (ch2 << 8) + (ch3 << 16) + (ch4 << 24));
    }

    public long readLong() throws IOException {
        short b1 = readUnsignedByte();
        short b2 = readUnsignedByte();
        short b3 = readUnsignedByte();
        short b4 = readUnsignedByte();
        short b5 = readUnsignedByte();
        short b6 = readUnsignedByte();
        short b7 = readUnsignedByte();
        short b8 = readUnsignedByte();

        int num = (b1 | (b2 << 8) | (b3 << 16) | (b4 << 24));
        int num2 = (b5 | (b6 << 8) | (b7 << 16) | (b8 << 24));

        return ((num | (long)num2 << 32));
    }

    public float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());
    }

    public double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }

    public String readString() throws IOException {
        int bytesLength = read7BitEncodedInt();

        byte[] buffer = new byte[bytesLength];
        if(read(buffer) < 0) throw new EOFException();

        return new String(buffer, encoding);
    }

    public TColor readColor() throws IOException {
        byte red = readByte();
        byte green = readByte();
        byte blue = readByte();

        return new TColor(red, green, blue);
    }

    // Ported from C#'s BinaryReader class
    public int read7BitEncodedInt() throws IOException {
        int count = 0;
        int shift = 0;
        boolean more = true;
        while(more) {
            if(shift == 35) throw new IllegalStateException();

            byte b = readByte();

            count |= (b & 0x7F) << shift;
            shift += 7;

            if((b & 0x80) == 0) more = false;
        }
        return count;
    }

    public long getPosition() {
        return position;
    }
}
