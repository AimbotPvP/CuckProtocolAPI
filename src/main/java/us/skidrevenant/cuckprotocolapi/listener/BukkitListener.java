package us.skidrevenant.cuckprotocolapi.listener;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import us.skidrevenant.cuckprotocolapi.CuckProtocolAPI;
import us.skidrevenant.cuckprotocolapi.adapter.ProtocolAdapter;
import us.skidrevenant.cuckprotocolapi.event.events.InboundPacketEvent;
import us.skidrevenant.cuckprotocolapi.packet.Packet;
import us.skidrevenant.cuckprotocolapi.packet.WrappedPacket;
import us.skidrevenant.cuckprotocolapi.packet.wrappers.EntityUseAction;
import us.skidrevenant.cuckprotocolapi.util.ReflectionUtil;

/**
 * @author SkidRevenant
 * at 26/04/2018
 */
public class BukkitListener implements Listener {

    @Getter
    private final ProtocolAdapter protocolAdapter;

    public BukkitListener() {
        this.protocolAdapter = CuckProtocolAPI.getInst().getProtocolAdapter();
        CuckProtocolAPI.getInst().getServer().getPluginManager().registerEvents(this, CuckProtocolAPI.getInst()); //Register our events
    }

    @EventHandler
    public void onInboundPacketEvent(InboundPacketEvent event) {
        WrappedPacket wrappedPacket = event.getWrappedPacket();

        Packet packet = wrappedPacket.getPacket(); //Gets the packet type

        long time = wrappedPacket.getTime(); //Gets the time the packet was sent

        switch (packet) {
            case USEENTITY:

                EntityUseAction entityUseAction = wrappedPacket.getEntityUseAction();

                switch (entityUseAction) {
                    case ATTACK:

                        break;
                }
                break;
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        this.getProtocolAdapter().addAdapter(ReflectionUtil.getNetworkManagerChannel(player), player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        this.getProtocolAdapter().removeAdapter(ReflectionUtil.getNetworkManagerChannel(player), player);
    }

}
