package me.king.chat.Comandos;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.king.chat.Utils.ChatManager;


public class GlobalCommand implements CommandExecutor {

	public static boolean chat = true;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		
		if(sender instanceof Player) {
			
			Player p = (Player) sender;
			
			if(cmd.getName().equalsIgnoreCase("g")) {
			
			if(args.length == 0) {
				p.sendMessage("§cUtilize /g <mensagem>");
				return true;
			}
			if (chat == false) {
				p.sendMessage("§c[!] O Chat está desabilitado");
				return false;
			}

			String message = "";
			for(int i = 0; i < args.length; i++) {
				if(p.hasPermission("kchat.vip")) {
				message = message + " " + args[i];
				message = message.replace("&", "§");
				}else {
					message = message + " " + args[i];
				}
				}
			for(Player all : Bukkit.getOnlinePlayers()) {

				if (p.isOp() == true) {
				
					all.sendMessage("");
					all.sendMessage("§c[G] §7" +ChatManager.getMagnata(p)+ "§7 "+ ChatManager.getGroup(p)+ "§7 "+ ChatManager.getrank(p)+ "§7 "+ ChatManager.getclanTag(p)+ "§7 "+ p.getName()+"§7: "+message);
					all.sendMessage("");
					return false;
				}
				all.sendMessage("§c[G] §7" +ChatManager.getMagnata(p)+ "§7 "+ ChatManager.getGroup(p)+ "§7 "+ ChatManager.getrank(p)+ "§7 "+ ChatManager.getclanTag(p)+ "§7 "+ p.getName()+"§7: "+message);
				
			}
		}
		}else {
			sender.sendMessage("§cComando somente para jogadores");
			return true;
		}
		
		return false;
	}

}
