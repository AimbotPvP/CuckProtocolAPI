package us.skidrevenant.cuckprotocolapi.util;

import us.skidrevenant.cuckprotocolapi.packet.PacketDirection;

/**
 * @author SkidRevenant
 * at 26/04/2018
 */
public class StringUtil {

    /**
     * Gets the proper replacement for the the {@param packetDirection} direction
     *
     * @param packetDirection                    The packet direction inbound means it comes from the client, outbounds means its sent from the server
     * @return                              The appropriate String to replace with
     */
    public static String getPacketReplacement(PacketDirection packetDirection) {
        return packetDirection == PacketDirection.INBOUND ? "PacketPlayIn" : "PacketPlayOut";
    }
}
