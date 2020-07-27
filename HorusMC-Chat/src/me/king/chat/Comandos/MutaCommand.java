package me.king.chat.Comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.king.chat.Utils.ChatManager;


public class MutaCommand implements CommandExecutor {

	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage("");
			return false;
		}
		if (cmd.getName().equalsIgnoreCase("mutar")) {

			Player p = (Player) sender;
			if (args.length == 0) {
				p.sendMessage("§c[!] /mutar <jogador>");
				return false;
			}
			Player jogador = Bukkit.getPlayerExact(args[0]);
			if (jogador.isOnline() == false) {
				p.sendMessage("§c[!] Jogador não encontrado!");
				return false;
			}
			for (Player all : Bukkit.getOnlinePlayers()) {
				
				all.sendMessage("§cParece que temos mais um detento!");
				all.sendMessage("");
				all.sendMessage("§cAutor: §f"+ p.getName());
				all.sendMessage("§cPunição: §fMute");
				all.sendMessage("§cDetento: §f"+ jogador.getName());
				ChatManager.Mutar(p);
			}
			return false;
		}

		return false;
	}

}
