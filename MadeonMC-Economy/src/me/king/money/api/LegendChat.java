package me.king.money.api;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import me.king.money.MetodosEconomy;


public class LegendChat implements Listener{
	
	
	@EventHandler
	private void onChat(ChatMessageEvent e) {
		Player p = e.getSender();
		String TGMT;
		String magnata = MetodosEconomy.Maganta();
		if (!magnata.equalsIgnoreCase(p.getName())) {
			TGMT = "";
		}else {
			TGMT = "§a[$] ";
		}
		if(e.getTags().contains("rico")) {
	        e.setTagValue("rico",TGMT);
		}
	}
	
}
