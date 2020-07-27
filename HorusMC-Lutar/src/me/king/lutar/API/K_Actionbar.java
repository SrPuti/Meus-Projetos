package me.king.lutar.API;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class K_Actionbar {
	public static void EnviarMenssagem(Player player, String message) {
		try {
			Constructor<?> cons = getNMSClass("PacketPlayOutChat")
					.getConstructor(new Class[] { getNMSClass("IChatBaseComponent"), Byte.TYPE });
			Object base = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0]
					.getMethod("a", new Class[] { String.class })
					.invoke(null, new Object[] { "{\"text\":\"" + message + "\"}" });
			Object packet = cons.newInstance(new Object[] { base, Byte.valueOf((byte) 2) });
			Object ePlayer = player.getClass().getMethod("getHandle", new Class[0]).invoke(player, new Object[0]);
			Object playerConnection = ePlayer.getClass().getField("playerConnection").get(ePlayer);
			playerConnection.getClass().getMethod("sendPacket", new Class[] { getNMSClass("Packet") })
					.invoke(playerConnection, new Object[] { packet });
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchFieldException | InstantiationException e) {
			e.printStackTrace();
		}
	}

	private static Class<?> getNMSClass(String name) {
		try {
			return Class.forName("net.minecraft.server." + getVersion() + "." + name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String getVersion() {
		return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
	}

}
