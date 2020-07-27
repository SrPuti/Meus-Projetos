package me.king.chat.Comandos;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.king.chat.Utils.ChatManager;

public class ToggleTellCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("toggletell")) {
					if(ChatManager.telloff.contains(p)) {
						ChatManager.desativar(p);
					} else {
						ChatManager.ativar(p);
					}
			
			}
			
		}else {
			sender.sendMessage("§cComando so para players!");
			return true;
		}
		
		return false;
	}
	
	
}
