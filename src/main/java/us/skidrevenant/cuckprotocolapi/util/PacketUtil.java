package us.skidrevenant.cuckprotocolapi.util;

import org.bukkit.entity.Player;
import us.skidrevenant.cuckprotocolapi.CuckProtocolAPI;
import us.skidrevenant.cuckprotocolapi.event.PacketEvent;
import us.skidrevenant.cuckprotocolapi.event.events.InboundPacketEvent;
import us.skidrevenant.cuckprotocolapi.event.events.OutboundPacketEvent;
import us.skidrevenant.cuckprotocolapi.packet.Packet;
import us.skidrevenant.cuckprotocolapi.packet.PacketDirection;
import us.skidrevenant.cuckprotocolapi.packet.WrappedPacket;
import us.skidrevenant.cuckprotocolapi.packet.wrappers.EntityUseAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author SkidRevenant
 * at 03/05/2018
 */
public class PacketUtil {

    /**
     *
     * Returns whether the {@param packet} should be cancelled or not
     *
     * @param msg                             The raw packet object
     * @param packetDirection                 The direction of {@param packet}
     * @param player                          The player sending or receiving the packet
     * @return                                True if {@see PacketUtil#shouldCancel}
     */
    public static boolean processPacket(Object msg, PacketDirection packetDirection, Player player) {
        WrappedPacket wrappedPacket = wrapPacket(msg, packetDirection);

        return !shouldCancel(packetDirection, wrappedPacket, player);
    }

    /**
     *
     * Returns whether the even is cancelled or not
     *
     * @param packetDirection                 The direction of {@param wrappedPacket}
     * @param wrappedPacket                   The wrapped object of the packet
     * @param player                          The player sending or receiving the packet
     * @return                                True if the event is cancelled
     */
    private static boolean shouldCancel(PacketDirection packetDirection, WrappedPacket wrappedPacket, Player player) {
        PacketEvent event = packetDirection == PacketDirection.INBOUND ?
                new InboundPacketEvent(wrappedPacket, player) :
                new OutboundPacketEvent(wrappedPacket, player);

        CuckProtocolAPI.getInst().getServer().getPluginManager().callEvent(event);

        return event.isCancelled();
    }

    /**
     * @param packet                          The raw packet object
     * @param packetDirection                 The direction of {@param packet}
     * @return                                A WrappedPacket from {@param packet}
     */
    private static WrappedPacket wrapPacket(Object packet, PacketDirection packetDirection) {
        try {

            List<Object> values = new ArrayList<>();

            Class<?> packetClass = packet.getClass();

            boolean isSubClass = packetClass.getSuperclass() != ReflectionUtil.getPacketClass();

            Arrays.stream(packetClass.getDeclaredFields())
                    .forEach(field -> {
                        field.setAccessible(true);

                        try {
                            Object fieldInstance = field.get(packet);

                            values.add(fieldInstance);

                            if (fieldInstance instanceof Enum) {
                                values.add(EntityUseAction.valueOf(((Enum) fieldInstance).name()));
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    });

            if (isSubClass) {

                Arrays.stream(packetClass.getSuperclass().getDeclaredFields())
                        .forEach(field -> {
                            field.setAccessible(true);
                            try {
                                values.add(field.get(packet));
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        });
            }

            String packetName = isSubClass ? packet.getClass().getSuperclass().getSimpleName() : packet.getClass().getSimpleName();

            return new WrappedPacket(Packet.get(packetDirection == PacketDirection.OUTBOUND ? packetName.concat("OUT") : packetName, packetDirection), packet, values, System.currentTimeMillis());
        } catch (Exception e) {

            if (e instanceof IllegalArgumentException) {
                String packetClassName = packet.getClass().getSimpleName().replace("PacketPlayOut", "")
                        .concat("Out");
                System.out.println(String.format("%s(PacketDirection.OUTBOUND, \"%s\")", packetClassName.toUpperCase(), packetClassName));
            } else {
                System.out.println("Error for packet: " + packet.getClass().getSimpleName());
                e.printStackTrace();
            }
            return new WrappedPacket(Packet.ERROR, packet, null, 0L);
        }
    }
}
