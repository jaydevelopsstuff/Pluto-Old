package net.jay.pluto.world;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// Consider migrating lunar data to somewhere else (like a manager)
@Getter
@Setter
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
    private int[] treeTops;
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

    public BoringWorldInfo() {

    }
}