package me.king.chat.Comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.king.chat.Eventos.ChatLocal;


public class ChatCommand implements CommandExecutor{

	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage("§c[!] Somente jogadores!");
				return false;
			}
			if (cmd.getName().equalsIgnoreCase("chat")) {
				if (!sender.hasPermission("kchat.admin")) {
					sender.sendMessage("§c[!] Você não possue permissão para executar esse comando!");
					return false;
				}
				Player p = (Player)sender;
			
				if (args.length == 0) {
					p.sendMessage("§c[!] /chat <Off/On>.");
					return false;
				}
				if (args[0].equalsIgnoreCase("Off")) {
					p.sendMessage("§c[!] Chat desativado com sucesso!");
					Bukkit.broadcastMessage("§c[!] O Chat Foi desativado!");
					GlobalCommand.chat = false;
					ChatLocal.chatlocal = false;
					return false;
				}
				if (args[0].equalsIgnoreCase("On")) {
					p.sendMessage("§aChat Ativado com sucesso!");
					Bukkit.broadcastMessage("§aO Chat Foi Ativado!");
					GlobalCommand.chat = true;
					ChatLocal.chatlocal = true;
					return false;
				}
			return false;	
			}
		return false;
	}
	
	
}
