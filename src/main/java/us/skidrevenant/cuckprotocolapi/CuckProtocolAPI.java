package us.skidrevenant.cuckprotocolapi;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import us.skidrevenant.cuckprotocolapi.adapter.ProtocolAdapter;
import us.skidrevenant.cuckprotocolapi.adapter.adapters.NewProtocolAdapter;
import us.skidrevenant.cuckprotocolapi.adapter.adapters.OldProtocolAdapter;
import us.skidrevenant.cuckprotocolapi.listener.BukkitListener;
import us.skidrevenant.cuckprotocolapi.util.ReflectionUtil;

import java.util.logging.Level;

/**
 * @author SkidRevenant
 * at 26/04/2018
 */
@Getter
public class CuckProtocolAPI extends JavaPlugin {

    @Getter
    private static CuckProtocolAPI inst; //The instance of our plugin to keep the code more clean by not passing down the instance to every single class that needs it

    private ProtocolAdapter protocolAdapter;

    private boolean outdatedNetty;

    @Override
    public void onEnable() {
        inst = this;

        final String serverVersion = ReflectionUtil.getServerVersion();

        this.getLogger().log(Level.INFO, String.format("Booting up CuckProtocol for CraftBukkit version: %s", serverVersion));

        this.outdatedNetty = Integer.valueOf(serverVersion
                .replace("v", "")
                .replace("_", "")
                .replace("R", "")) < 180;

        this.protocolAdapter = this.outdatedNetty ? new OldProtocolAdapter() : new NewProtocolAdapter();

        this.getLogger().log(Level.INFO, String.format("Current netty version for CraftBukkit version %s is %s",
                serverVersion,
                outdatedNetty ? "outdated" : "not outdated"));

        new BukkitListener();
    }
}
