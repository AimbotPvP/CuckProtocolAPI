package us.skidrevenant.cuckprotocolapi.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import us.skidrevenant.cuckprotocolapi.packet.WrappedPacket;

/**
 * @author SkidRevenant
 * at 27/04/2018
 */
@Getter
@Setter
@RequiredArgsConstructor
public abstract class PacketEvent extends Event implements Cancellable {

    //Our wrapped packet which contains all our values
    private final WrappedPacket wrappedPacket;

    private final Player player;

    //If we should cancel the desired packet or not
    private boolean cancelled;

}