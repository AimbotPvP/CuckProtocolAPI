package us.skidrevenant.cuckprotocolapi.event.events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import us.skidrevenant.cuckprotocolapi.event.PacketEvent;
import us.skidrevenant.cuckprotocolapi.packet.WrappedPacket;

/**
 * @author SkidRevenant
 * at 27/04/2018
 */
public class InboundPacketEvent extends PacketEvent {

    private static final HandlerList handlers = new HandlerList();

    public InboundPacketEvent(WrappedPacket packet, Player player) {
        super(packet, player);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
