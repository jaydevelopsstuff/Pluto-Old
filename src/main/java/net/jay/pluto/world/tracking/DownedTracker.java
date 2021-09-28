package net.jay.pluto.world.tracking;

public class DownedTracker {
    public static final int DefaultArrayLength = 34;

    /**
     * Order: Clown, King Slime, Eye of Cthulhu, Goblins, Skeletron, Eater of Worlds/Brain of Cthulhu, Queen Bee,
     * Wall of Flesh, Queen Slime, Destroyer, Twins, Skeletron Prime, Pirates, Plantera, Golem, Mourning Wood,
     * Pumpking, Pumpkin Moon, Everscream, Santank, Ice Queen, Frost Moon, Duke Fishron, DD2 Invasion T1,
     * DD2 Invasion T2, DD2 Invasion T3, Empress of Light, Martians, Lunatic Cultist, Solar Pillar, Vortex Pillar,
     * Nebula Pillar, Stardust Pillar, Moon Lord
     */
    private boolean[] downedData;

    public DownedTracker() {
        downedData = new boolean[DefaultArrayLength];
    }

    public DownedTracker(boolean[] downedData) {
        this.downedData = downedData;
    }

    public boolean isClownDowned() {
        return downedData[0];
    }

    public boolean isKingSlimeDowned() {
        return downedData[1];
    }

    public boolean isEyeDowned() {
        return downedData[2];
    }

    public boolean areGoblinsDowned() {
        return downedData[3];
    }

    public boolean isSkeletronDowned() {
        return downedData[4];
    }

    public boolean isWorldEvilBossDowned() {
        return downedData[5];
    }

    public boolean isQueenBeeDowned() {
        return downedData[6];
    }

    public boolean isWallOfFleshDowned() {
        return downedData[7];
    }

    public void setClownDowned(boolean downed) {
        downedData[0] = downed;
    }

    public void setKingSlimeDowned(boolean downed) {
        downedData[1] = downed;
    }

    public void setEyeDowned(boolean downed) {
        downedData[2] = downed;
    }

    public void setGoblinsDowned(boolean downed) {
        downedData[3] = downed;
    }

    public void setSkeletronDowned(boolean downed) {
        downedData[4] = downed;
    }

    public void setWorldEvilBossDowned(boolean downed) {
        downedData[5] = downed;
    }

    public void setQueenBeeDowned(boolean downed) {
        downedData[6] = downed;
    }

    public void setWallOfFleshDowned(boolean downed) {
        downedData[7] = downed;
    }

    public void setQueenSlimeDowned(boolean downed) {
        downedData[8] = downed;
    }

    public void setDestroyerDowned(boolean downed) {
        downedData[9] = downed;
    }

    public void setTwinsDowned(boolean downed) {
        downedData[10] = downed;
    }

    public void setSkeletronPrimeDowned(boolean downed) {
        downedData[11] = downed;
    }

    public void setPiratesDowned(boolean downed) {
        downedData[12] = downed;
    }

    public void setPlanteraDowned(boolean downed) {
        downedData[13] = downed;
    }

    public void setGolemDowned(boolean downed) {
        downedData[14] = downed;
    }

    public void setMourningWoodDowned(boolean downed) {
        downedData[15] = downed;
    }

    public void setPumpkingDowned(boolean downed) {
        downedData[16] = downed;
    }

    public void setPumpkinMoonDowned(boolean downed) {
        downedData[15] = downed;
        downedData[16] = downed;
        downedData[17] = downed;
    }

    public void setEverscreamDowned(boolean downed) {
        downedData[18] = downed;
    }

    public void setSantankDowned(boolean downed) {
        downedData[19] = downed;
    }

    public void setIceQueenDowned(boolean downed) {
        downedData[20] = downed;
    }

    public void setFrostMoonDowned(boolean downed) {
        downedData[18] = downed;
        downedData[19] = downed;
        downedData[20] = downed;
        downedData[21] = downed;
    }

    public void setDukeFishronDowned(boolean downed) {
        downedData[22] = downed;
    }

    public void setDD2T1Downed(boolean downed) {
        downedData[23] = downed;
    }

    public void setDD2T2Downed(boolean downed) {
        downedData[24] = downed;
    }

    public void setDD2T3Downed(boolean downed) {
        downedData[25] = downed;
    }

    public void setEmpressOfLightDowned(boolean downed) {
        downedData[26] = downed;
    }

    public void setMartiansDowned(boolean downed) {
        downedData[27] = downed;
    }

    public void setLunaticCultistDowned(boolean downed) {
        downedData[28] = downed;
    }

    public void setSolarPillarDowned(boolean downed) {
        downedData[29] = downed;
    }

    public void setVortexPillarDowned(boolean downed) {
        downedData[30] = downed;
    }

    public void setNebulaPillarDowned(boolean downed) {
        downedData[31] = downed;
    }

    public void setStardustPillarDowned(boolean downed) {
        downedData[32] = downed;
    }

    public void setMoonLordDowned(boolean downed) {
        downedData[33] = downed;
    }

    public boolean[] getDownedData() {
        return downedData;
    }

    public void setDownedData(boolean[] downedData) {
        this.downedData = downedData;
    }
}
