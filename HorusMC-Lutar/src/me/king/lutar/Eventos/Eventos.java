package me.king.lutar.Eventos;


import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import me.king.lutar.Main;
import me.king.lutar.API.API;
import me.king.lutar.Comandos.ComandosMembro;


public class Eventos implements Listener{

	public static Integer Kills ;

	public static Main m = (Main)Bukkit.getPluginManager().getPlugin("HorusLutar");
	
	@EventHandler
	public void mito(EntityDeathEvent  e) {
		Player matou = e.getEntity().getKiller();
		Player morreu = e.getEntity().getKiller();
		if(morreu.getName().equalsIgnoreCase(m.getConfig().getString("Mito_Atual"))) {
			m.getConfig().set("Mito_Atual", matou.getName());
			matou.sendMessage("§eVocê e mais Novo do Mito do PVP!");
			morreu.sendMessage("§eVocê não e mais o Mito do PVP!");
			Bukkit.broadcastMessage("§aO Jogador §f" + matou.getName() + "§a Torno-se o novo §cMito Do PvP!");
			matou.getLocation().getWorld().playSound(matou.getLocation(), Sound.ENDERDRAGON_GROWL, 0.5F, 0.5F);
			if (API.Check("saida")) {
				matou.sendMessage("§cO Local De Saida Não Foi Setado!");
				morreu.sendMessage("§cO Local De Saida Não Foi Setado!");
			}else {
			API.Teleporte(matou, "saida");
			API.Teleporte(morreu, "saida");
			matou.sendMessage("§aLuta Finalizada!");
			morreu.sendMessage("§aLuta Finalizada!");
			morreu.getInventory().clear();
			matou.getInventory().clear();
			ComandosMembro.emX1.remove(matou);
			ComandosMembro.emX1.remove(morreu);
			ComandosMembro.x1.remove(matou);
			ComandosMembro.x1.remove(morreu);
			
		}
		}
	
	}
	@EventHandler
	void AO(PlayerJoinEvent e) {
		Kills = 0;
	}
	
	@SuppressWarnings("unused")
	@EventHandler
	void Matar(EntityDeathEvent e) {
		if (e.getEntity() instanceof Player) {
			if (e.getEntity().getKiller() instanceof Player) {
				int qnt = Kills;
				qnt = +1;
			}
		}
		
	}
	@EventHandler
	void falar(ChatMessageEvent e) {
		if (e.getTags().contains("mito")) {
			e.setTagValue("mito", m.getConfig().getString("Mito_Atual"));
		}
	}
	
}
