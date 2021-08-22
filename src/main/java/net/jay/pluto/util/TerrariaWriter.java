package net.jay.pluto.util;

import net.jay.pluto.net.PacketBuffer;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class TerrariaWriter extends OutputStream {
    private static final Charset encoding = StandardCharsets.UTF_8;

    private final OutputStream out;

    private final byte[] writeBuffer = new byte[8];

    public TerrariaWriter(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    public void writeBoolean(boolean b) throws IOException {
        out.write(b ? 1 : 0);
    }

    public void writeByte(byte b) throws IOException {
        out.write(b);
    }

    public void writeBytes(byte[] b) throws IOException {
        out.write(b);
    }

    public void writeShort(short s) throws IOException {
        writeBuffer[0] = (byte)(s);
        writeBuffer[1] = (byte)(s >>> 8);

        out.write(writeBuffer, 0, 2);
    }

    public void writeInt(int i) throws IOException {
        writeBuffer[0] = (byte)(i);
        writeBuffer[1] = (byte)(i >>>  8);
        writeBuffer[2] = (byte)(i >>> 16);
        writeBuffer[3] = (byte)(i >>> 24);

        out.write(writeBuffer, 0, 4);
    }

    public void writeLong(long l) throws IOException {
        writeBuffer[0] = (byte)(l);
        writeBuffer[1] = (byte)(l >>>  8);
        writeBuffer[2] = (byte)(l >>> 16);
        writeBuffer[3] = (byte)(l >>> 24);
        writeBuffer[4] = (byte)(l >>> 32);
        writeBuffer[5] = (byte)(l >>> 40);
        writeBuffer[6] = (byte)(l >>> 48);
        writeBuffer[7] = (byte)(l >>> 56);

        out.write(writeBuffer, 0, 8);
    }

    public void writeFloat(float f) throws IOException {
        writeInt(Float.floatToIntBits(f));
    }

    public void writeDouble(double d) throws IOException {
        writeLong(Double.doubleToLongBits(d));
    }

    public void writeString(String s) throws IOException {
        byte[] stringBytes = s.getBytes(StandardCharsets.UTF_8);
        write7BitEncodedInt(stringBytes.length);
        write(stringBytes);
    }

    public void writeBuffer(PacketBuffer buffer) throws IOException {
        writeBytes(buffer.getBuffer());
    }

    @Override
    public void flush() throws IOException {
        out.flush();
    }

    @Override
    public void close() throws IOException {
        out.close();
    }

    private void write7BitEncodedInt(int i) throws IOException {
        int num;
        for (num = i; num >= 128; num >>= 7)
        {
            write((byte)(num | 0x80));
        }
        write((byte)num);
    }
}
