package us.skidrevenant.cuckprotocolapi.packet;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import us.skidrevenant.cuckprotocolapi.util.StringUtil;

/**
 * @author SkidRevenant
 * at 26/04/2018
 */
@RequiredArgsConstructor
public enum Packet {

    FLYING(PacketDirection.INBOUND, "Flying"),
    LOOK(PacketDirection.INBOUND, "Look"),
    POSITION(PacketDirection.INBOUND, "Position"),
    POSITIONLOOK(PacketDirection.INBOUND, "PositionLook"),

    KEEPALIVE(PacketDirection.INBOUND, "KeepAlive"),
    ENTITYACTION(PacketDirection.INBOUND, "EntityAction"),

    ARMANIMATION(PacketDirection.INBOUND, "ArmAnimation"),
    BLOCKPLACE(PacketDirection.INBOUND, "BlockPlace"),
    USEENTITY(PacketDirection.INBOUND, "UseEntity"),


    SETCREATIVESLOT(PacketDirection.INBOUND, "SetCreativeSlot"),

    CLIENTCOMMAND(PacketDirection.INBOUND, "ClientCommand"),
    BLOCKDIG(PacketDirection.INBOUND, "BlockDig"),
    HELDITEMSLOT(PacketDirection.INBOUND, "HeldItemSlot"),
    ABILITIES(PacketDirection.INBOUND, "Abilities"),
    CUSTOMPAYLOAD(PacketDirection.INBOUND, "CustomPayload"),
    SETTINGS(PacketDirection.INBOUND, "Settings"),

    CHAT(PacketDirection.INBOUND, "Chat"),

    ENTITYHEADROTATIONOUT(PacketDirection.OUTBOUND, "EntityHeadRotationOut"),
    ENTITYLOOKOUT(PacketDirection.OUTBOUND, "EntityLookOut"),
    ENTITYVELOCITYOUT(PacketDirection.OUTBOUND, "EntityVelocityOut"),
    ENTITYDESTROYOUT(PacketDirection.OUTBOUND, "EntityDestroyOut"),
    ENTITYEFFECTOUT(PacketDirection.OUTBOUND, "EntityEffectOut"),
    ENTITYMETADATAOUT(PacketDirection.OUTBOUND, "EntityMetaDataOut"),
    ENTITYSTATUSOUT(PacketDirection.OUTBOUND, "EntityStatusOut"),
    ENTITYTELEPORTOUT(PacketDirection.OUTBOUND, "EntityTeleportOut"),
    ENTITYEQUIPMENTOUT(PacketDirection.OUTBOUND, "EntityEquipmentOut"),

    POSITIONOUT(PacketDirection.OUTBOUND, "PositionOut"),
    KEEPALIVEOUT(PacketDirection.OUTBOUND, "KeepAliveOut"),

    RELENTITYMOVEOUT(PacketDirection.OUTBOUND, "RelEntityMoveOut"),
    RELENTITYMOVELOOKOUT(PacketDirection.OUTBOUND, "RelEntityMoveLookOut"),

    CHATOUT(PacketDirection.OUTBOUND, "ChatOut"),

    MAPOUT(PacketDirection.OUTBOUND, "MapOut"),
    MAPCHUNKOUT(PacketDirection.OUTBOUND, "MapChunkOut"),
    MAPCHUNKBULKOUT(PacketDirection.OUTBOUND, "MapChunkBulkOut"),

    NAMEDSOUNDEFFECTOUT(PacketDirection.OUTBOUND, "NamedSoundEffectOut"),
    NAMEDENTITYSPAWNOUT(PacketDirection.OUTBOUND, "NamedEntitySpawnOut"),

    PLAYERINFOOUT(PacketDirection.OUTBOUND, "PlayerInfoOut"),
    PLAYERLISTHEADERFOOTEROUT(PacketDirection.OUTBOUND, "PlayerListHeaderFooterOut"),

    EXPERIENCEOUT(PacketDirection.OUTBOUND, "ExperienceOut"),

    UPDATEHEALTHOUT(PacketDirection.OUTBOUND, "UpdateHealthOut"),
    UPDATEATTRIBUTESOUT(PacketDirection.OUTBOUND, "UpdateAttributesOut"),
    UPDATETIMEOUT(PacketDirection.OUTBOUND, "UpdateTimeOut"),
    UPDATEENTITYNBTOUT(PacketDirection.OUTBOUND, "UpdateEntityNBTOut"),
    UPDATESIGNOUT(PacketDirection.OUTBOUND, "UpdateSignOut"),

    ANIMATIONOUT(PacketDirection.OUTBOUND, "AnimationOut"),

    SETSLOTOUT(PacketDirection.OUTBOUND, "SetsLotOut"),

    BLOCKCHANGEOUT(PacketDirection.OUTBOUND, "BlockChangeOut"),
    BLOCKACTIONOUT(PacketDirection.OUTBOUND, "BlockActionOut"),
    BLOCKBREAKANIMATIONOUT(PacketDirection.OUTBOUND, "BlockBreakAnimationOut"),
    MULTIBLOCKCHANGEOUT(PacketDirection.OUTBOUND, "MultiBlockChangeOut"),

    SPAWNENTITYLIVINGOUT(PacketDirection.OUTBOUND, "SpawnEntityLivingOut"),
    SPAWNENTITYOUT(PacketDirection.OUTBOUND, "SpawnEntityOut"),
    SPAWNENTITYEXPERIENCEORBOUT(PacketDirection.OUTBOUND, "SppawnEntityExperienceOrbOut"),
    SPAWNENTITYWEATHEROUT(PacketDirection.OUTBOUND, "SpawnEntityWeatherOut"),

    COLLECTOUT(PacketDirection.OUTBOUND, "CollectOut"),

    REMOVEENTITYEFFECTOUT(PacketDirection.OUTBOUND, "RemoveEntityEffectOut"),

    ABILITIESOUT(PacketDirection.OUTBOUND, "AbilitiesOut"),
    ATTACHENTITYOUT(PacketDirection.OUTBOUND, "AttachEntityOut"),
    BEDOUT(PacketDirection.OUTBOUND, "BedOut"),

    CAMERAOUT(PacketDirection.OUTBOUND, "CameraOut"),
    COMBATEVENTOUT(PacketDirection.OUTBOUND, "CombatEventOut"),
    CUSTOMPAYLOADOUT(PacketDirection.OUTBOUND, "CustomPayloadOut"),

    EXPLOSIONOUT(PacketDirection.OUTBOUND, "ExplosionOut"),
    GAMESTATECHANGEOUT(PacketDirection.OUTBOUND, "GameStateChangeOut"),
    KICKDISCONNECTOUT(PacketDirection.OUTBOUND, "KickDisconnectOut"),
    OPENSIGNEDITOROUT(PacketDirection.OUTBOUND, "OpenSignEditorOut"),

    RESOURCEPACKSENDOUT(PacketDirection.OUTBOUND, "ResourcePackSendOut"),
    RESPAWNOUT(PacketDirection.OUTBOUND, "RespawnOut"),

    SCOREBOARDDISPLAYOBJECTIVEOUT(PacketDirection.OUTBOUND, "ScoreboardDisplayObjectiveOut"),
    SCOREBOARDOBJECTIVEOUT(PacketDirection.OUTBOUND, "ScoreboardObjectiveOut"),
    SCOREBOARDSCOREOUT(PacketDirection.OUTBOUND, "ScoreboardScoreOut"),
    SCOREBOARDTEAMOUT(PacketDirection.OUTBOUND, "ScoreboardTeamOut"),

    SETTINGSOUT(PacketDirection.OUTBOUND, "SettingsOut"),

    SERVERDIFFICULTYOUT(PacketDirection.OUTBOUND, "ServerDifficultyOut"),
    SETCOMPRESSIONOUT(PacketDirection.OUTBOUND, "SetCompressionOut"),

    STATISTICOUT(PacketDirection.OUTBOUND, "StatisticOut"),
    TABCOMPLETEOUT(PacketDirection.OUTBOUND, "TabCompleteOut"),
    TILEENTITYDATAOUT(PacketDirection.OUTBOUND, "TileEntityDataOut"),
    TITLEOUT(PacketDirection.OUTBOUND, "TitleOut"),

    WINDOWDATAOUT(PacketDirection.OUTBOUND, "WindowDataOut"),
    CLOSEWINDOWOUT(PacketDirection.OUTBOUND, "CloseWindowOut"),
    OPENWINDOWOUT(PacketDirection.OUTBOUND, "OpenWindowOut"),
    WINDOWCLICKOUT(PacketDirection.OUTBOUND, "WindowClickOut"),
    WINDOWITEMSOUT(PacketDirection.OUTBOUND, "WindowItemsOut"),

    WORLDEVENTOUT(PacketDirection.OUTBOUND, "WorldEventOut"),
    WORLDPARTICLESOUT(PacketDirection.OUTBOUND, "WorldParticlesOut"),
    WORLDBORDEROUT(PacketDirection.OUTBOUND, "WorldBorderOut"),

    ERROR(PacketDirection.INBOUND, "ERROR");

    final PacketDirection packetDirection;

    @Getter
    final String name;

    public static Packet get(String packet, PacketDirection packetDirection) {
        return Packet.valueOf(packet.replace(StringUtil.getPacketReplacement(packetDirection), "").toUpperCase());
    }
}
