package us.skidrevenant.cuckprotocolapi.util;

import lombok.Getter;

import org.bukkit.Bukkit;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import us.skidrevenant.cuckprotocolapi.CuckProtocolAPI;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author SkidRevenant
 * at 26/04/2018
 */
public class ReflectionUtil {

    @Getter
    private static String serverVersion;

    @Getter
    private static Class<?> packetClass;

    private static Class<?> craftPlayerClass,
    entityPlayerClass,
    playerConnectionClass,
    networkManagerClass,
    channelClass;

    public static Field channel;

    private static Field playerConnectionField;
    private static Field networkManagerField;

    static {
        /*
        * The package name serverVersion
        * For example: v1_8_R3
        */
        serverVersion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

        try {
            craftPlayerClass = Class.forName("org.bukkit.craftbukkit." + serverVersion + ".entity.CraftPlayer");
            entityPlayerClass = Class.forName("net.minecraft.server." + serverVersion + ".EntityPlayer");
            playerConnectionClass = Class.forName("net.minecraft.server." + serverVersion + ".PlayerConnection");
            networkManagerClass = Class.forName("net.minecraft.server." + serverVersion + ".NetworkManager");

            packetClass = Class.forName("net.minecraft.server." + serverVersion + ".Packet");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Unable to find class: " + e.getMessage() + " outdated version? version: " + serverVersion);
            Bukkit.getServer().getPluginManager().disablePlugin(CuckProtocolAPI.getInst());
        }

        try {
            channelClass = Class.forName("io.netty.channel.Channel");

        } catch (ClassNotFoundException e) {
            try {
                channelClass = Class.forName("net.minecraft.util.io.netty.channel.Channel");
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }



        try {
            playerConnectionField = entityPlayerClass.getDeclaredField("playerConnection");
            networkManagerField = playerConnectionClass.getDeclaredField("networkManager");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        channel = Arrays.stream(networkManagerClass.getDeclaredFields())
                .filter(Objects::nonNull)
                .filter(field -> field.getType() == channelClass)
                .findFirst()
                .orElse(null);

        channel.setAccessible(true);
    }

    /**
     * Gets the specified NMS class {@param name}
     *
     * @param name                    The NMS class name
     * @return                        The {@param name} NMS class
     * @throws ClassNotFoundException When the class tries to be loaded
     * but no definition for the class with the specified name could be found
     */
    public static Class<?> getNMSClass(String name) throws ClassNotFoundException {
        return Class.forName("net.minecraft.server." + serverVersion + "." + name);
    }

    /**
     * Gets the specified CraftBukkit class {@param name}
     *
     * @param name                    The CraftBukkit class name
     * @return                        The {@param name} CraftBukkit class
     * @throws ClassNotFoundException When the class tries to be loaded
     * but no definition for the class with the specified name could be found
     */
    public static Class<?> getCBClass(String name) throws ClassNotFoundException {
        return Class.forName("org.bukkit.craftbukkit." + serverVersion + "." + name);
    }

    /**
     * Gets the EntityPlayer object from {@param player}
     *
     * @param player                 The player to get the EntityPlayer instance of
     * @return                       The EntityPlayer object from {@param player}
     */
    public static Object getEntityPlayer(Player player) {
        try {
            return craftPlayerClass.getDeclaredMethod("getHandle").invoke(player);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Gets the PlayerConnection object from {@param player}
     *
     * @param player                 The player to get the PlayerConnection instance of
     * @return                       The PlayerConnection object instance from {@param player}
     */
    public static Object getPlayerConnection(Player player) {
        try {
            return playerConnectionField.get(getEntityPlayer(player));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Gets the NetworkManager object from {@param player}
     *
     * @param player                 The player to get the NetworkManager instance of
     * @return                       The NetworkManager object instance from {@param player}
     */
    public static Object getNetworkManager(Player player) {
        try {
            return networkManagerField.get(getPlayerConnection(player));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Gets the Channel object instance from {@param player}
     *
     * @param player                 The player to get the Channel object from
     * @return                       The Packet Queue Channel found in the NetworkManager class
     */
    public static Object getNetworkManagerChannel(Player player) {
        try {
            return channel.get(getNetworkManager(player));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

}
