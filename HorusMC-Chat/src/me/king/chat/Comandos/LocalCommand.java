package me.king.chat.Comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import me.king.chat.Eventos.ChatLocal;
import me.king.chat.Utils.ChatManager;

public class LocalCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {

		if (!(sender instanceof Player))
			return false;

		if (cmd.getName().equalsIgnoreCase("l")) {
			Player p = (Player) sender;
			if (args.length == 0) {
				p.sendMessage("§c[!] /l <menssagem>");
				return false;
			}
			if (args.length > 0) {

				for (Entity alle : p.getNearbyEntities(20, 20, 20)) {
					if (ChatLocal.chatlocal == false) {
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
					String Format = "§e[L] §7" +ChatManager.getMagnata(p)+ "§7 "+ ChatManager.getGroup(p)+ "§7 "+ ChatManager.getrank(p)+ "§7 "+ ChatManager.getclanTag(p)+ "§7 "+ p.getName()+"§e: "+ message;
					if (alle instanceof Player) {
						if (p.isOp() == true) {
							Player all = (Player) alle;
							p.sendMessage("");
							p.sendMessage(Format);
							p.sendMessage("");
							
							all.sendMessage("");
							all.sendMessage(Format);
							all.sendMessage("");	
						return false;
						}
						return false;
					}
					return false;
				}

				return false;
			}
			return false;
		}
		return false;
	}
}
