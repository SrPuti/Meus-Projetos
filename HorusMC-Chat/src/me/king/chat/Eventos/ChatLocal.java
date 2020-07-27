package me.king.chat.Eventos;

import java.util.ArrayList;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

import me.king.chat.Main;
import me.king.chat.Utils.ChatManager;

@SuppressWarnings("deprecation")
public class ChatLocal implements Listener {

	public static boolean chatlocal = true;
	public static ArrayList<Player> chat_delay = new ArrayList<Player>();
	Main plugin;

	@EventHandler
	public void chatFormat1(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
			if (chatlocal == false) {
				p.sendMessage("§c[!] O Chat está desabilitado");
				e.setFormat("");
				return;
			}

			if (p.hasPermission("kchat.vip") || p.isOp() == true) {
				
				e.setFormat("§e[L] §7" +ChatManager.getMagnata(p)+ "§7 "+ ChatManager.getGroup(p)+ "§7 "+ ChatManager.getrank(p)+ "§7 "+ ChatManager.getclanTag(p)+ "§e "+ p.getName()+"§7: "+ e.getMessage().replace("&", "§"));

			} else {
				e.setFormat("§e[L] §7" +ChatManager.getMagnata(p)+ "§7 "+ ChatManager.getGroup(p)+ "§7 "+ ChatManager.getrank(p)+ "§7 "+ ChatManager.getclanTag(p)+ "§e "+ p.getName()+"§7: "+ e.getMessage());

			}
			// <cargo> <rank> <clã> <nick>
			
			Entity ent = null;
			for (Entity alle : p.getNearbyEntities(15, 15, 15)) {
				ent = alle;
			}
			if (ent instanceof Player) {
				Player all = (Player)ent;
				if (p.isOp() == true) {
					p.sendMessage("");
					p.sendMessage(e.getFormat());
					p.sendMessage("");
					
					all.sendMessage("");
					all.sendMessage(e.getFormat());
					all.sendMessage("");	
					return;
				}else {
					p.sendMessage(e.getFormat());
					all.sendMessage(e.getFormat());
				}
				
			}else {
				if (p.isOp() == true) {
					p.sendMessage("");
					p.sendMessage(e.getFormat());
					p.sendMessage("");
					p.sendMessage("§cVocê está falando sozinho seu loco!");
					return;
				}else {
					p.sendMessage(e.getFormat());
					p.sendMessage("§cVocê está falando sozinho seu loco!");
				}
				
			}

	}
	@EventHandler
	void chat (PlayerChatEvent e) {
		e.setCancelled(true);
	}
}
