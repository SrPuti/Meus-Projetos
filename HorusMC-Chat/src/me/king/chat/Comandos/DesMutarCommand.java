package me.king.chat.Comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.king.chat.Utils.ChatManager;


public class DesMutarCommand implements CommandExecutor {

	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage("");
			return false;
		}
		if (cmd.getName().equalsIgnoreCase("desmutar")) {

			Player p = (Player) sender;
			if (args.length == 0) {
				p.sendMessage("§c[!] /desmutar <jogador>");
				return false;
			}
			Player jogador = Bukkit.getPlayerExact(args[0]);
			if (jogador.isOnline() == false) {
				p.sendMessage("§c[!] Jogador não encontrado!");
				return false;
			}
			ChatManager.desMutar(p);
				p.sendMessage("§aJogador §7'"+ jogador.getName()+"'§a desmutacom sucesso!");
			return false;
		}

		return false;
	}

}
