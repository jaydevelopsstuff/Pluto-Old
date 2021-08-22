package net.jay.pluto.util;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class TerrariaReader extends InputStream {
    private static final Charset encoding = StandardCharsets.UTF_8;

    private final InputStream in;

    public TerrariaReader(InputStream in) {
        this.in = in;
    }

    @Override
    public int available() throws IOException {
        return in.available();
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    public final void readFully(byte b[], int off, int len) throws IOException {
        Objects.checkFromIndexSize(off, len, b.length);
        int n = 0;
        while (n < len) {
            int count = in.read(b, off + n, len - n);
            if (count < 0)
                throw new EOFException();
            n += count;
        }
    }

    public boolean readBoolean() throws IOException {
        return read() != 0;
    }

    public byte readByte() throws IOException {
        byte b = (byte)in.read();

        if(b == -1) throw new EOFException();

        return b;
    }

    public short readShort() throws IOException {
        int ch1 = in.read();
        int ch2 = in.read();
        if ((ch1 | ch2) < 0)
            throw new EOFException();
        return (short)((ch1) + (ch2 << 8));
    }

    public int readInt() throws IOException {
        int ch1 = in.read();
        int ch2 = in.read();
        int ch3 = in.read();
        int ch4 = in.read();
        if ((ch1 | ch2 | ch3 | ch4) < 0)
            throw new EOFException();
        return ((ch1) + (ch2 << 8) + (ch3 << 16) + (ch4 << 24));
    }

    public long readLong() throws IOException {
        byte[] readBuffer = new byte[8];

        readFully(readBuffer, 0, 8);

        int num = (readBuffer[0] | (readBuffer[1] << 8) | (readBuffer[2] << 16) | (readBuffer[3] << 24));
        int num2 = (readBuffer[4] | (readBuffer[5] << 8) | (readBuffer[6] << 16) | (readBuffer[7] << 24));
        return (((long)num2 << 32) | num);
    }

    public float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());
    }

    public double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }

    public String readString() throws IOException {
        byte[] m_charBytes = new byte[128];
        char[] m_charBuffer = new char[512];

        int readBuffer = 0;
        int stringLength = read7BitEncodedInt();

        if(stringLength < 0) throw new IllegalStateException();
        if(stringLength == 0) return "";

        StringBuilder stringBuilder = null;
        do
        {
            int count = (Math.min(stringLength - readBuffer, 128));
            int num3 = in.read(m_charBytes, 0, count);
            if (num3 == 0) throw new EOFException();

            int chars = 0;
            for(char character : new String(m_charBytes, encoding).toCharArray()) {
                m_charBuffer[chars] = character;
                chars++;
            }
            if (readBuffer == 0 && num3 == stringLength)
            {
                return new String(m_charBuffer, 0, chars);
            }
            if (stringBuilder == null)
            {
                stringBuilder = new StringBuilder(Math.min(stringLength, 360));
            }
            stringBuilder.append(m_charBuffer, 0, chars);
            readBuffer += num3;
        }
        while (readBuffer < stringLength);
        return stringBuilder.toString();
    }

    // Ported from C#'s BinaryReader class
    public int read7BitEncodedInt() throws IOException {
        int count = 0;
        int shift = 0;
        boolean more = true;
        while (more) {
            byte b = readByte();
            count |= (b & 0x7F) << shift;
            shift += 7;
            if((b & 0x80) == 0) {
                more = false;
            }
        }
        return count;
    }
}
