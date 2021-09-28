package net.jay.pluto.creative;

public enum CreativePowers {
        TimeSetFrozen(0),
        // time_setdawn = 1,
        // time_setnoon = 2,
        // time_setdusk = 3,
        // time_setmidnight = 4,
        GodMode(5),
        // wind_setstrength = 6,
        // rain_setstrength = 7,
        TimeSetSpeed(8),
        RainSetFrozen(9),
        WindSetFrozen(10),
        IncreasePlacementRange(11),
        SetDifficulty(12),
        BiomeSpreadSetFrozen(13),
        SetSpawnRate(14);

        public final int ID;

        CreativePowers(int ID) {
            this.ID = ID;
        }

        public static CreativePowers fromID(int ID) {
            for(CreativePowers creativePower : values()) {
                if(creativePower.ID == ID) return creativePower;
            }
            return null;
        }
}
