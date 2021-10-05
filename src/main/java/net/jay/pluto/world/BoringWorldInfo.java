package net.jay.pluto.world;

// Consider migrating lunar data to somewhere else (like a manager)
public class BoringWorldInfo {
    private boolean[] tileImportants;

    private byte moonType;
    private int moonPhase;
    private int treeX0;
    private int treeX1;
    private int treeX2;
    private int treeStyle0;
    private int treeStyle1;
    private int treeStyle2;
    private int treeStyle3;
    private int caveBackX0;
    private int caveBackX1;
    private int caveBackX2;
    private int caveBackStyle0;
    private int caveBackStyle1;
    private int caveBackStyle2;
    private int caveBackStyle3;
    private int iceBackStyle;
    private int jungleBackStyle;
    private int hellBackStyle;
    private double worldSurface;
    private double rockLayer;
    private boolean spawnMeteor;
    private boolean shadowOrbSmashed;
    private byte shadowOrbCount;
    private int altarCount;
    private int invasionDelay;
    private int invasionSize;
    private int invasionStartSize;
    private int invasionType;
    private double invasionX;
    private double slimeRainTime;
    // Can be byte?
    private short sundialCooldown;
    private int rainTime;
    private float maxRain;
    private int hardmodeOre1TileID;
    private int hardmodeOre2TileID;
    private int hardmodeore3TileID;
    private int bgTree;
    private int bgCorruption;
    private int bgJungle;
    private int bgSnow;
    private int bgHallow;
    private int bgCrimson;
    private int bgDesert;
    private int bgOcean;
    private int cloudBackgroundActive;
    private short numClouds;
    private float windSpeedTarget;
    private int anglerQuest;
    private int cultistDelay;
    private boolean fastForwardTime;
    private boolean solarPillarActive;
    private boolean vortexPillarActive;
    private boolean nebulaPillarActive;
    private boolean stardustPillarActive;
    private boolean lunarApocalypse;
    private boolean partyManual;
    private boolean partyGenuine;
    private int partyCooldown;
    private boolean sandstormActive;
    private int sandstormTimeLeft;
    private float sandstormSeverity;
    private float sandstormIntendedSeverity;
    private int bgMushroom;
    private int bgUnderworld;
    private int bgTree2;
    private int bgTree3;
    private int bgTree4;
    private boolean combatBookUsed;
    private int lanternNightCooldown;
    private boolean lanternNightGenuine;
    private boolean lanternNightManual;
    private boolean lanternNightNextGenuine;
    private boolean forceHalloweenForToday;
    private boolean forceXMasForToday;
    private int ore1TileID;
    private int ore2TileID;
    private int ore3TileID;
    private int ore4TileID;
    private boolean boughtCat;
    private boolean boughtDog;
    private boolean boughtBunny;

    public boolean[] getTileImportants() {
        return tileImportants;
    }

    public void setTileImportants(boolean[] tileImportants) {
        this.tileImportants = tileImportants;
    }

    public byte getMoonType() {
        return moonType;
    }

    public void setMoonType(byte moonType) {
        this.moonType = moonType;
    }

    public int getMoonPhase() {
        return moonPhase;
    }

    public void setMoonPhase(int moonPhase) {
        this.moonPhase = moonPhase;
    }

    public int getTreeX0() {
        return treeX0;
    }

    public void setTreeX0(int treeX0) {
        this.treeX0 = treeX0;
    }

    public int getTreeX1() {
        return treeX1;
    }

    public void setTreeX1(int treeX1) {
        this.treeX1 = treeX1;
    }

    public int getTreeX2() {
        return treeX2;
    }

    public void setTreeX2(int treeX2) {
        this.treeX2 = treeX2;
    }

    public int getTreeStyle0() {
        return treeStyle0;
    }

    public void setTreeStyle0(int treeStyle0) {
        this.treeStyle0 = treeStyle0;
    }

    public int getTreeStyle1() {
        return treeStyle1;
    }

    public void setTreeStyle1(int treeStyle1) {
        this.treeStyle1 = treeStyle1;
    }

    public int getTreeStyle2() {
        return treeStyle2;
    }

    public void setTreeStyle2(int treeStyle2) {
        this.treeStyle2 = treeStyle2;
    }

    public int getTreeStyle3() {
        return treeStyle3;
    }

    public void setTreeStyle3(int treeStyle3) {
        this.treeStyle3 = treeStyle3;
    }

    public int getCaveBackX0() {
        return caveBackX0;
    }

    public void setCaveBackX0(int caveBackX0) {
        this.caveBackX0 = caveBackX0;
    }

    public int getCaveBackX1() {
        return caveBackX1;
    }

    public void setCaveBackX1(int caveBackX1) {
        this.caveBackX1 = caveBackX1;
    }

    public int getCaveBackX2() {
        return caveBackX2;
    }

    public void setCaveBackX2(int caveBackX2) {
        this.caveBackX2 = caveBackX2;
    }

    public int getCaveBackStyle0() {
        return caveBackStyle0;
    }

    public void setCaveBackStyle0(int caveBackStyle0) {
        this.caveBackStyle0 = caveBackStyle0;
    }

    public int getCaveBackStyle1() {
        return caveBackStyle1;
    }

    public void setCaveBackStyle1(int caveBackStyle1) {
        this.caveBackStyle1 = caveBackStyle1;
    }

    public int getCaveBackStyle2() {
        return caveBackStyle2;
    }

    public void setCaveBackStyle2(int caveBackStyle2) {
        this.caveBackStyle2 = caveBackStyle2;
    }

    public int getCaveBackStyle3() {
        return caveBackStyle3;
    }

    public void setCaveBackStyle3(int caveBackStyle3) {
        this.caveBackStyle3 = caveBackStyle3;
    }

    public int getIceBackStyle() {
        return iceBackStyle;
    }

    public void setIceBackStyle(int iceBackStyle) {
        this.iceBackStyle = iceBackStyle;
    }

    public int getJungleBackStyle() {
        return jungleBackStyle;
    }

    public void setJungleBackStyle(int jungleBackStyle) {
        this.jungleBackStyle = jungleBackStyle;
    }

    public int getHellBackStyle() {
        return hellBackStyle;
    }

    public void setHellBackStyle(int hellBackStyle) {
        this.hellBackStyle = hellBackStyle;
    }

    public double getWorldSurface() {
        return worldSurface;
    }

    public void setWorldSurface(double worldSurface) {
        this.worldSurface = worldSurface;
    }

    public double getRockLayer() {
        return rockLayer;
    }

    public void setRockLayer(double rockLayer) {
        this.rockLayer = rockLayer;
    }

    public boolean isShadowOrbSmashed() {
        return shadowOrbSmashed;
    }

    public void setShadowOrbSmashed(boolean shadowOrbSmashed) {
        this.shadowOrbSmashed = shadowOrbSmashed;
    }

    public boolean isSpawnMeteor() {
        return spawnMeteor;
    }

    public void setSpawnMeteor(boolean spawnMeteor) {
        this.spawnMeteor = spawnMeteor;
    }

    public byte getShadowOrbCount() {
        return shadowOrbCount;
    }

    public void setShadowOrbCount(byte shadowOrbCount) {
        this.shadowOrbCount = shadowOrbCount;
    }

    public int getAltarCount() {
        return altarCount;
    }

    public void setAltarCount(int altarCount) {
        this.altarCount = altarCount;
    }

    public int getInvasionDelay() {
        return invasionDelay;
    }

    public void setInvasionDelay(int invasionDelay) {
        this.invasionDelay = invasionDelay;
    }

    public int getInvasionSize() {
        return invasionSize;
    }

    public void setInvasionSize(int invasionSize) {
        this.invasionSize = invasionSize;
    }

    public int getInvasionType() {
        return invasionType;
    }

    public void setInvasionType(int invasionType) {
        this.invasionType = invasionType;
    }

    public double getInvasionX() {
        return invasionX;
    }

    public void setInvasionX(double invasionX) {
        this.invasionX = invasionX;
    }

    public double getSlimeRainTime() {
        return slimeRainTime;
    }

    public void setSlimeRainTime(double slimeRainTime) {
        this.slimeRainTime = slimeRainTime;
    }

    public short getSundialCooldown() {
        return sundialCooldown;
    }

    public void setSundialCooldown(short sundialCooldown) {
        this.sundialCooldown = sundialCooldown;
    }

    public int getRainTime() {
        return rainTime;
    }

    public void setRainTime(int rainTime) {
        this.rainTime = rainTime;
    }

    public float getMaxRain() {
        return maxRain;
    }

    public void setMaxRain(float maxRain) {
        this.maxRain = maxRain;
    }

    public int getHardmodeOre1TileID() {
        return hardmodeOre1TileID;
    }

    public void setHardmodeOre1TileID(int hardmodeOre1TileID) {
        this.hardmodeOre1TileID = hardmodeOre1TileID;
    }

    public int getHardmodeOre2TileID() {
        return hardmodeOre2TileID;
    }

    public void setHardmodeOre2TileID(int hardmodeOre2TileID) {
        this.hardmodeOre2TileID = hardmodeOre2TileID;
    }

    public int getHardmodeore3TileID() {
        return hardmodeore3TileID;
    }

    public void setHardmodeore3TileID(int hardmodeore3TileID) {
        this.hardmodeore3TileID = hardmodeore3TileID;
    }

    public int getBgTree() {
        return bgTree;
    }

    public void setBgTree(int bgTree) {
        this.bgTree = bgTree;
    }

    public int getBgCorruption() {
        return bgCorruption;
    }

    public void setBgCorruption(int bgCorruption) {
        this.bgCorruption = bgCorruption;
    }

    public int getBgJungle() {
        return bgJungle;
    }

    public void setBgJungle(int bgJungle) {
        this.bgJungle = bgJungle;
    }

    public int getBgSnow() {
        return bgSnow;
    }

    public void setBgSnow(int bgSnow) {
        this.bgSnow = bgSnow;
    }

    public int getBgHallow() {
        return bgHallow;
    }

    public void setBgHallow(int bgHallow) {
        this.bgHallow = bgHallow;
    }

    public int getBgCrimson() {
        return bgCrimson;
    }

    public void setBgCrimson(int bgCrimson) {
        this.bgCrimson = bgCrimson;
    }

    public int getBgDesert() {
        return bgDesert;
    }

    public void setBgDesert(int bgDesert) {
        this.bgDesert = bgDesert;
    }

    public int getBgOcean() {
        return bgOcean;
    }

    public void setBgOcean(int bgOcean) {
        this.bgOcean = bgOcean;
    }

    public int getCloudBackgroundActive() {
        return cloudBackgroundActive;
    }

    public void setCloudBackgroundActive(int cloudBackgroundActive) {
        this.cloudBackgroundActive = cloudBackgroundActive;
    }

    public short getNumClouds() {
        return numClouds;
    }

    public void setNumClouds(short numClouds) {
        this.numClouds = numClouds;
    }

    public float getWindSpeedTarget() {
        return windSpeedTarget;
    }

    public void setWindSpeedTarget(float windSpeedTarget) {
        this.windSpeedTarget = windSpeedTarget;
    }

    public int getAnglerQuest() {
        return anglerQuest;
    }

    public void setAnglerQuest(int anglerQuest) {
        this.anglerQuest = anglerQuest;
    }

    public int getInvasionStartSize() {
        return invasionStartSize;
    }

    public void setInvasionStartSize(int invasionStartSize) {
        this.invasionStartSize = invasionStartSize;
    }

    public int getCultistDelay() {
        return cultistDelay;
    }

    public void setCultistDelay(int cultistDelay) {
        this.cultistDelay = cultistDelay;
    }

    public boolean isFastForwardTime() {
        return fastForwardTime;
    }

    public void setFastForwardTime(boolean fastForwardTime) {
        this.fastForwardTime = fastForwardTime;
    }

    public boolean isSolarPillarActive() {
        return solarPillarActive;
    }

    public void setSolarPillarActive(boolean solarPillarActive) {
        this.solarPillarActive = solarPillarActive;
    }

    public boolean isVortexPillarActive() {
        return vortexPillarActive;
    }

    public void setVortexPillarActive(boolean vortexPillarActive) {
        this.vortexPillarActive = vortexPillarActive;
    }

    public boolean isNebulaPillarActive() {
        return nebulaPillarActive;
    }

    public void setNebulaPillarActive(boolean nebulaPillarActive) {
        this.nebulaPillarActive = nebulaPillarActive;
    }

    public boolean isStardustPillarActive() {
        return stardustPillarActive;
    }

    public void setStardustPillarActive(boolean stardustPillarActive) {
        this.stardustPillarActive = stardustPillarActive;
    }

    public boolean isLunarApocalypse() {
        return lunarApocalypse;
    }

    public void setLunarApocalypse(boolean lunarApocalypse) {
        this.lunarApocalypse = lunarApocalypse;
    }

    public boolean isPartyManual() {
        return partyManual;
    }

    public void setPartyManual(boolean partyManual) {
        this.partyManual = partyManual;
    }

    public boolean isPartyGenuine() {
        return partyGenuine;
    }

    public void setPartyGenuine(boolean partyGenuine) {
        this.partyGenuine = partyGenuine;
    }

    public int getPartyCooldown() {
        return partyCooldown;
    }

    public void setPartyCooldown(int partyCooldown) {
        this.partyCooldown = partyCooldown;
    }

    public boolean isSandstormActive() {
        return sandstormActive;
    }

    public void setSandstormActive(boolean sandstormActive) {
        this.sandstormActive = sandstormActive;
    }

    public int getSandstormTimeLeft() {
        return sandstormTimeLeft;
    }

    public void setSandstormTimeLeft(int sandstormTimeLeft) {
        this.sandstormTimeLeft = sandstormTimeLeft;
    }

    public float getSandstormSeverity() {
        return sandstormSeverity;
    }

    public void setSandstormSeverity(float sandstormSeverity) {
        this.sandstormSeverity = sandstormSeverity;
    }

    public float getSandstormIntendedSeverity() {
        return sandstormIntendedSeverity;
    }

    public void setSandstormIntendedSeverity(float sandstormIntendedSeverity) {
        this.sandstormIntendedSeverity = sandstormIntendedSeverity;
    }

    public int getBgMushroom() {
        return bgMushroom;
    }

    public void setBgMushroom(int bgMushroom) {
        this.bgMushroom = bgMushroom;
    }

    public int getBgUnderworld() {
        return bgUnderworld;
    }

    public void setBgUnderworld(int bgUnderworld) {
        this.bgUnderworld = bgUnderworld;
    }

    public int getBgTree2() {
        return bgTree2;
    }

    public void setBgTree2(int bgTree2) {
        this.bgTree2 = bgTree2;
    }

    public int getBgTree3() {
        return bgTree3;
    }

    public void setBgTree3(int bgTree3) {
        this.bgTree3 = bgTree3;
    }

    public int getBgTree4() {
        return bgTree4;
    }

    public void setBgTree4(int bgTree4) {
        this.bgTree4 = bgTree4;
    }

    public boolean isCombatBookUsed() {
        return combatBookUsed;
    }

    public void setCombatBookUsed(boolean combatBookUsed) {
        this.combatBookUsed = combatBookUsed;
    }

    public int getLanternNightCooldown() {
        return lanternNightCooldown;
    }

    public void setLanternNightCooldown(int lanternNightCooldown) {
        this.lanternNightCooldown = lanternNightCooldown;
    }

    public boolean isLanternNightGenuine() {
        return lanternNightGenuine;
    }

    public void setLanternNightGenuine(boolean lanternNightGenuine) {
        this.lanternNightGenuine = lanternNightGenuine;
    }

    public boolean isLanternNightManual() {
        return lanternNightManual;
    }

    public void setLanternNightManual(boolean lanternNightManual) {
        this.lanternNightManual = lanternNightManual;
    }

    public boolean isLanternNightNextGenuine() {
        return lanternNightNextGenuine;
    }

    public void setLanternNightNextGenuine(boolean lanternNightNextGenuine) {
        this.lanternNightNextGenuine = lanternNightNextGenuine;
    }

    public boolean isForceHalloweenForToday() {
        return forceHalloweenForToday;
    }

    public void setForceHalloweenForToday(boolean forceHalloweenForToday) {
        this.forceHalloweenForToday = forceHalloweenForToday;
    }

    public boolean isForceXMasForToday() {
        return forceXMasForToday;
    }

    public void setForceXMasForToday(boolean forceXMasForToday) {
        this.forceXMasForToday = forceXMasForToday;
    }

    public int getOre1TileID() {
        return ore1TileID;
    }

    public void setOre1TileID(int ore1TileID) {
        this.ore1TileID = ore1TileID;
    }

    public int getOre2TileID() {
        return ore2TileID;
    }

    public void setOre2TileID(int ore2TileID) {
        this.ore2TileID = ore2TileID;
    }

    public int getOre3TileID() {
        return ore3TileID;
    }

    public void setOre3TileID(int ore3TileID) {
        this.ore3TileID = ore3TileID;
    }

    public int getOre4TileID() {
        return ore4TileID;
    }

    public void setOre4TileID(int ore4TileID) {
        this.ore4TileID = ore4TileID;
    }

    public boolean isBoughtCat() {
        return boughtCat;
    }

    public void setBoughtCat(boolean boughtCat) {
        this.boughtCat = boughtCat;
    }

    public boolean isBoughtDog() {
        return boughtDog;
    }

    public void setBoughtDog(boolean boughtDog) {
        this.boughtDog = boughtDog;
    }

    public boolean isBoughtBunny() {
        return boughtBunny;
    }

    public void setBoughtBunny(boolean boughtBunny) {
        this.boughtBunny = boughtBunny;
    }
}
