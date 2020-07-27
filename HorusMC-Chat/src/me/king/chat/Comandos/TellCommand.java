package me.king.chat.Comandos;



import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.king.chat.Utils.ChatManager;


public class TellCommand implements CommandExecutor {

	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {

		if (!(sender instanceof Player)) {
		sender.sendMessage("§c[!] Somente jogadores!");
			return false;
		}
		if (cmd.getName().equalsIgnoreCase("tell")) {
			Player p = (Player)sender;
			if (args.length <= 1) {
				p.sendMessage("§c[!] /tell <jogador> <menssagem>.");
				return false;
			}
			Player  jogador = Bukkit.getPlayerExact(args[0]);
			String message = "";
			for(int i = 1; i < args.length; i++) {
				if(p.hasPermission("kchat.vip")) {
				message = message + " " + args[i];
				message = message.replace("&", "§");
				}else {
					message = message + " " + args[i];
				}
				}
			if(ChatManager.telloff.contains(p)) {
				p.sendMessage("§aVoce esta com tell desativado");
				return true;
			}
			
			if(ChatManager.telloff.contains(jogador)) {
				p.sendMessage("§aEste jogador esta com tell desativado");
				return true;
			}
			if (jogador.isOnline() == false) {
				p.sendMessage("§c[!] Jogador não encontrado!");
				return false;
			}
			if (jogador.getName() == p.getName()) {
				p.sendMessage("§c[!] Você não pode enviar menssagem para si mesmo! ");
				return false;
			}
			jogador.sendMessage("§eDe §f" + p.getName() + "§e para §f" + jogador.getName() + "§7: " + message);
			p.sendMessage("§eDe §f" + p.getName() + "§e para §f" + jogador.getName() + "§7: " + message);
			}
		return false;
	}
	
}
