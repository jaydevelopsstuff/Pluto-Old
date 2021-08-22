package net.jay.pluto.localization;

import net.jay.pluto.net.PacketBuffer;
import net.jay.pluto.util.TerrariaWriter;

import java.io.IOException;

public class NetworkText {
    public static final NetworkText Empty = new NetworkText("", Mode.LITERAL);

    private String text;
    private Mode mode;

    private NetworkText[] substitutions;

    public NetworkText(String text, Mode mode) {
        this.text = text;
        this.mode = mode;
    }

    public PacketBuffer serialize(PacketBuffer buffer) {
        buffer.writeByte(mode.ID);
        buffer.writeString(text);
        return buffer;
    }

    public void serialize(TerrariaWriter writer) throws IOException {
        writer.writeByte(mode.ID);
        writer.writeString(text);
    }

    public enum Mode {
        LITERAL((byte)0),
        FORMATTABLE((byte)1),
        LOCALIZATIONKEY((byte)2);

        public final byte ID;

        Mode(byte ID) {
            this.ID = ID;
        }
    }
}
