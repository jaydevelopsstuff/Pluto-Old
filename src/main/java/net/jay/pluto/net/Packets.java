package net.jay.pluto.net;

import net.jay.pluto.net.packet.CPacket;
import net.jay.pluto.net.packet.Packet;
import net.jay.pluto.net.packet.packets.both.PlayerInfo;
import net.jay.pluto.net.packet.packets.both.PlayerSlot;
import net.jay.pluto.net.packet.packets.client.ClientUUID;
import net.jay.pluto.net.packet.packets.client.ConnectRequest;
import net.jay.pluto.net.packet.packets.client.RequestWorldData;

/** An enum for all the Packet Types in Terraria, this was a pain to make so you better appreciate it :) */
public enum Packets {
    ConnectRequest(1, From.CLIENT),
    Disconnect(2, From.SERVER),
    ContinueConnecting(3, From.SERVER),
    PlayerInfo(4, From.BOTH),
    PlayerSlot(5, From.BOTH),
    RequestWorldData(6, From.CLIENT), // AKA ContinueConnecting2
    WorldInfo(7, From.SERVER),
    RequestEssentialTiles(8, From.CLIENT),
    Status(9, From.SERVER),
    SendTileSection(10, From.SERVER),
    SectionTileFrame(11, From.SERVER),
    PlayerSpawn(12, From.CLIENT),
    PlayerUpdate(13, From.BOTH),
    PlayerActive(14, From.SERVER),
    PlayerHp(16, From.BOTH),
    Tile(17, From.BOTH),
    TimeSet(18, From.SERVER),
    DoorUse(19, From.BOTH),
    TileSendSquare(20, From.BOTH),
    ItemDrop(21, From.BOTH),
    ItemOwner(22, From.BOTH),
    NpcUpdate(23, From.SERVER),
    NpcItemStrike(24, From.BOTH),
    ProjectileNew(27, From.BOTH),
    NpcStrike(28, From.BOTH),
    ProjectileDestroy(29, From.BOTH),
    TogglePvp(30, From.BOTH),
    OpenChest(31, From.CLIENT),
    UpdateChestItem(32, From.BOTH),
    SyncActiveChest(33, From.BOTH),
    PlaceChest(34, From.BOTH),
    EffectHeal(35, From.BOTH),
    PlayerZone(36, From.BOTH),
    PasswordRequired(37, From.SERVER),
    PasswordSend(38, From.CLIENT),
    RemoveItemOwner(39, From.SERVER),
    NpcTalk(40, From.BOTH),
    PlayerItemAnimation(41, From.BOTH),
    PlayerMana(41, From.BOTH),
    EffectMana(42, From.BOTH),
    PlayerTeam(45, From.BOTH),
    SignRead(46, From.CLIENT),
    SignNew(47, From.BOTH), // This guy's weird
    LiquidSet(48, From.BOTH),
    PlayerSpawnSelf(49, From.SERVER),
    PlayerBuff(50, From.BOTH),
    NpcSpecialEffect(51, From.BOTH),
    ChestUnlock(52, From.BOTH),
    NpcAddBuff(53, From.BOTH),
    NpcUpdateBuff(54, From.SERVER),
    PlayerAddBuff(55, From.BOTH),
    UpdateNPCName(56, From.BOTH),
    UpdateGoodEvil(57, From.SERVER),
    PlayMusicItem(58, From.BOTH),
    HitSwitch(59, From.BOTH),
    UpdateNPCHome(60, From.BOTH),
    SpawnBossInvasion(61, From.CLIENT),
    PlayerDodge(62, From.BOTH),
    PaintTile(63, From.BOTH),
    PaintWall(64, From.BOTH),
    Teleport(65, From.BOTH),
    PlayerHealOther(66, From.BOTH),
    Placeholder(67, From.BOTH), // Special packet type
    ClientUUID(68, From.CLIENT),
    GetChestName(69, From.BOTH),
    CatchNPC(70, From.CLIENT),
    ReleaseNPC(71, From.CLIENT),
    TravellingMerchantInventory(72, From.SERVER),
    TeleportationPotion(73, From.BOTH),
    AnglerQuest(74, From.SERVER),
    CompleteAnglerQuest(75, From.CLIENT),
    NumberOfAnglerQuestsCompleted(76, From.CLIENT),
    CreateTemporaryAnimation(77, From.SERVER),
    ReportInvasionProgress(78, From.SERVER),
    PlaceObject(79, From.BOTH),
    SyncPlayerChestIndex(80, From.SERVER),
    CreateCombatText(81, From.SERVER),
    LoadNetModule(82, From.CLIENT),
    NpcKillCount(83, From.SERVER),
    PlayerStealth(84, From.BOTH),
    ForceItemIntoNearestChest(85, From.CLIENT),
    UpdateTileEntity(86, From.SERVER),
    PlaceTileEntity(87, From.CLIENT),
    TweakItem(88, From.SERVER),
    PlaceItemFrame(89, From.CLIENT),
    UpdateItemDrop(90, From.BOTH),
    EmoteBubble(91, From.SERVER),
    SyncExtraValue(92, From.BOTH),
    SocialHandshake(93, From.BOTH), // Unknown and unused
    Deprecated(94, From.BOTH), // Unknown and unused
    KillPortal(95, From.CLIENT),
    PlayerTeleportPortal(96, From.BOTH),
    NotifyPlayerNpcKilled(97, From.SERVER),
    NotifyPlayerOfEvent(98, From.SERVER),
    UpdateMinionTarget(99, From.BOTH),
    NpcTeleportPortal(100, From.BOTH),
    UpdateShieldStrengths(101, From.SERVER),
    NebulaLevelUp(102, From.BOTH),
    MoonLordCountdown(103, From.SERVER),
    NpcShopItem(104, From.SERVER),
    GemLockToggle(105, From.CLIENT),
    PoofOfSmoke(106, From.SERVER),
    SmartTextMessage(107, From.SERVER),
    WiredCannonShot(108, From.SERVER),
    MassWireOperation(109, From.CLIENT),
    MassWireOperationPay(110, From.SERVER),
    ToggleParty(111, From.CLIENT),
    TreeGrowFX(112, From.BOTH),
    CrystalInvasionStart(113, From.CLIENT),
    CrystalInvasionWipeAll(114, From.SERVER),
    MinionAttackTargetUpdate(115, From.CLIENT),
    CrystalInvasionSendWaitTime(116, From.SERVER),
    PlayerHurtV2(117, From.CLIENT),
    PlayerDeathV2(118, From.CLIENT),
    CreateCombatTextExtended(119, From.BOTH),
    Emoji(120, From.CLIENT),
    TileEntityDisplayDollItemSync(121, From.BOTH),
    RequestTileEntityInteraction(122, From.BOTH),
    WeaponsRackTryPlacing(123, From.CLIENT),
    TileEntityHatRackItemSync(124, From.BOTH),
    SyncTilePicking(125, From.BOTH),
    SyncRevengeMarker(126, From.SERVER),
    RemoveRevengeMarker(127, From.SERVER),
    LandGolfBallInCup(128, From.BOTH),
    FinishedConnectingToServer(129, From.SERVER),
    FishOutNPC(130, From.CLIENT),
    TamperWithNPC(131, From.SERVER),
    PlayLegacySound(132, From.SERVER),
    FoodPlatterTryPlacing(133, From.CLIENT),
    UpdatePlayerLuckFactors(134, From.BOTH),
    DeadPlayer(135, From.SERVER),
    SyncCavernMonsterType(136, From.BOTH),
    RequestNPCBuffRemoval(137, From.CLIENT),
    ClientSyncedInventory(138, From.CLIENT),
    SetCountsAsHostForGameplay(139, From.SERVER);

    public final int ID;
    public final From from;

    Packets(int ID, From from) {
        this.ID = ID;
        this.from = from;
    }

    public static Packet getPacketAndSetData(PacketBuffer buffer) {
        Packets packet = fromID(buffer.readByte());

        return getPacketAndSetData(packet, buffer);
    }

    public static Packet getPacketAndSetData(Packets packet, PacketBuffer buffer) {
        return switch(packet) {
            case ConnectRequest -> new ConnectRequest(buffer);
            case PlayerInfo -> new PlayerInfo(buffer);
            case PlayerSlot -> new PlayerSlot(buffer);
            case RequestWorldData -> new RequestWorldData();
            case ClientUUID -> new ClientUUID(buffer);
            default -> null;
        };
    }

    public static Packets fromID(int ID) {
        for(Packets packet : values()) {
            if(packet.ID == ID) return packet;
        }
        return null;
    }

    public enum From {
        CLIENT,
        SERVER,
        BOTH
    }
}
