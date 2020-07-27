package me.king.money.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.king.money.MetodosEconomy;

public class MagnataComando implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage("");
			return false;
		}
		if (cmd.getName().equalsIgnoreCase("magnata")) {

			Player p = (Player) sender;
			if (args.length == 0) {
				if (MetodosEconomy.Maganta() == null) {
					p.sendMessage("§cNão possue nenhum magnata no servidor!");
					return false;
				} else {
					p.sendMessage("§aMagnata: §7'" + MetodosEconomy.Maganta() + "'");
					return false;
				}
			}
			
			
			return false;
		}

		return false;
	}
}
