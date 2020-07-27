package me.king.lutar.Comandos;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.king.lutar.API.API;

public class ComandosAdmin implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage("");
			return false;
		}
		if (cmd.getName().equalsIgnoreCase("x1admin")) {
			Player p = (Player)sender;
			if (args.length == 0) {
				p.sendMessage("§7Lista de comandos:");
				p.sendMessage("");
				p.sendMessage("§a/x1admin setarlocais <camarote, local1, local2 é saida> §7- Setar Locais.");
				p.sendMessage("§a/x1admin resetar <jogador> §7- Resetar informações.");
				return false;
			}
			if (args[0].equalsIgnoreCase("SetarLocais")) {
				
				//0 1 2 3
				if (args.length <= 1) {
					p.sendMessage("§a/x1admin setarlocais <camarote, local1, local2 é saida>.");
					return false;
				}
				if (args[1].equalsIgnoreCase("camarote")) {
					if (API.Check("camarote")) {
						p.sendMessage("§c[!] Esse local já está setado!");
						return false;
					}
					
					API.SetLocal(p, "camarote");
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 0.5F, 0.5F);
					p.sendMessage("§aO §7'Camarote'§a foi setado com sucesso!");
					return false;
				}else
				
				if (args[1].equalsIgnoreCase("saida")) {
					if (API.Check("saida")) {
						p.sendMessage("§c[!] Esse local já está setado!");
						return false;
					}
					
					API.SetLocal(p, "saida");
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 0.5F, 0.5F);
					p.sendMessage("§aO §7'Saída'§a foi setado com sucesso!");
					return false;
				}else 
				
					if (args[1].equalsIgnoreCase("local1")) {
						if (API.Check("local1")) {
							p.sendMessage("§c[!] Esse local já está setado!");
							return false;
						}
						
						API.SetLocal(p, "local1");
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 0.5F, 0.5F);
						p.sendMessage("§aA §7'Primeira Posição'§a foi setado com sucesso!");
						return false;
					}else
				
				if (args[1].equalsIgnoreCase("local2")) {
					if (API.Check("local2")) {
						p.sendMessage("§c[!] Esse local já está setado!");
						return false;
					}
					
					API.SetLocal(p, "local2");
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 0.5F, 0.5F);
					p.sendMessage("§aA §7'Segunda Posição'§a foi setado com sucesso!");
					return false;
				}
				
				return false;
			}
			if (args[0].equalsIgnoreCase("resetar")) {
				if (args.length <= 2) {
					p.sendMessage("§a/x1admin resetar <jogador>.");
					return false;
				}
				
				
				return false;
			}
			
			return false;
		}
		
		return false;
	}

	
	

}
