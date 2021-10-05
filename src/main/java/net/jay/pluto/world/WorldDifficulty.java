package net.jay.pluto.world;

public enum WorldDifficulty {
    Journey(3),
    Classic(0),
    Expert(1),
    Master(2);

    public final byte ID;

    WorldDifficulty(int ID) {
        this.ID = (byte)ID;
    }

    public static WorldDifficulty fromID(int ID) {
        for(WorldDifficulty worldDifficulty : values()) {
            if(worldDifficulty.ID == ID) return worldDifficulty;
        }
        return null;
    }
}
