package me.king.lutar.Comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.king.lutar.Main;


public class MitoComando implements CommandExecutor {

	private static Main m = (Main)Bukkit.getPluginManager().getPlugin("HorusLutar");
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
	
		if (!(sender instanceof Player)) {

			return false;

		}

		
		Player p = (Player)sender;

		if(cmd.getName().equalsIgnoreCase("mito")) {
			if(m.getConfig().getString("Mito_Atual").equals("Nenhum")) {
				p.sendMessage("§cNenhum Mito Atual");
			}else {
			p.sendMessage("§eO Mito Atual é: §f" + m.getConfig().getString("Mito_Atual"));
			}
		}

		return false;
	}
	
}
