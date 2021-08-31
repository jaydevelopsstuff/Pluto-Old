package net.jay.pluto.data.enums;

public enum CharacterDifficulty {
    Softcore((byte)0),
    Mediumcore((byte)1),
    Hardcore((byte)2),
    Creative((byte)8); // AKA Journey mode

    public final byte flag;

    CharacterDifficulty(byte flag) {
        this.flag = flag;
    }
}
