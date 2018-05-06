package us.skidrevenant.cuckprotocolapi.adapter;

import org.bukkit.entity.Player;

/**
 * @author SkidRevenant
 * at 03/05/2018
 */
public interface ProtocolAdapter {

    void addAdapter(Object channelObject, Player player);

    void removeAdapter(Object channelObject, Player player);
}
