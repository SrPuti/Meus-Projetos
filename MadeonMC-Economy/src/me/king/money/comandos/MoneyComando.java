package me.king.money.comandos;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.king.money.MetodosEconomy;

public class MoneyComando implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {

		if (!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage("§c[!] Comando apenas para jogaodres da rede!");
			return false;
		}
		if (cmd.getName().equalsIgnoreCase("money")) {
			Player p = (Player) sender;

			if (args.length == 0) {
				Double teste;
				teste = MetodosEconomy.PegarMoney(p.getName());
				p.sendMessage("§eCoins: §a" + MetodosEconomy.format(teste.longValue()));
				return false;
			}
			if (args[0].equalsIgnoreCase("ver")) {

				if (args.length <= 2) {
					p.sendMessage("§c[!] Use: /money ver <jogador>");
					return false;
				}
				Player jogador = Bukkit.getPlayerExact(args[1]);
				if (jogador == null) {
					p.sendMessage("§c[!] Jogador não encontrado!");

					return false;
				}
				p.sendMessage("§7" + jogador.getName() + " §epossue §a" + MetodosEconomy.PegarMoney(jogador.getName()+ " coins"));
				return false;

			}

			if (args[0].equalsIgnoreCase("ajuda")) {

				p.sendMessage("§7Lista De Comandos:");
				p.sendMessage("§f");
				p.sendMessage("§e/money ajuda §7- Ver todos os comandos do plugin.");
				p.sendMessage("§e/money setar §7- Setar uma quantia exata de money.");
				p.sendMessage("§e/money adicionar §7- Adicionar uma quantia de money.");
				p.sendMessage("§e/money remover §7- Remover uma quantia de money.");
				p.sendMessage("§e/money enviar §7- Envia uma quantia para outro jogador.");
				p.sendMessage("§e/money top §7- Lista de Jogadores mais ricos.");
				p.sendMessage("§e/money ver §7- Visualizar money de outro jogador.");
				p.sendMessage("§e/money resetall §7- resetar o money de todos os jogadores.");

				return false;
			}

			if (args[0].equalsIgnoreCase("top")) {

				List<String> lista = MetodosEconomy.GetMoneyTop();
				p.sendMessage("§aTop mais ricos do servidor:");
				p.sendMessage("");
				p.sendMessage("");
				for (String TopList : lista) {
					p.sendMessage(" " + TopList);
				}
				p.sendMessage("");
				return false;
			}
			if (args[0].equalsIgnoreCase("enviar")) {

				if (args.length <= 2) {
					p.sendMessage("§c[!] Use: /money enviar <jogador> <quantia>.");
					return false;
				}
				Player jogador = Bukkit.getPlayerExact(args[1]);
				Double quantia;
				try {
					quantia = Double.valueOf(args[2]);
				} catch (NumberFormatException e) {
					sender.sendMessage("§c[!] Digite um numero valido!");
					return true;
				}
				if (jogador == null) {
					p.sendMessage("§c[!] Jogador não encontrado!");
					return false;
				}
				if (jogador.getName() == p.getName()) {

					p.sendMessage("§c[!] Você não pode enviar money para você mesmo!");
					return false;
				}
				int nblock = 100000000;
				if (nblock >= quantia) {
					p.sendMessage("§c[!] Você não pode setar essa quantia!");
					return false;
				}
				if (quantia < 0) {
					p.sendMessage("§c[!] Você não pode enviar a quantia de 0 coins!");
					return false;
				}
				MetodosEconomy.setar(jogador.getName(), quantia);
				MetodosEconomy.remove(p.getName(), quantia);
				p.sendMessage("§eVocê envio §a" + MetodosEconomy.format(quantia.longValue()) + " coins §e para conta §f"
						+ jogador.getName() + "§e com sucesso!");

			}
			if (!sender.hasPermission("keconomy.admin")) {
				p.sendMessage("§c[!] Você não tem permissão para executar esse comando!");
				return false;
			} else {
				if (args[0].equalsIgnoreCase("setar")) {

					if (args.length <= 2) {
						p.sendMessage("§c[!] Use: /money setar <jogador> <quantia>.");
						return false;
					}
					Player jogador = Bukkit.getPlayerExact(args[1]);
					Double quantia;
					try {
						quantia = Double.valueOf(args[2]);
					} catch (NumberFormatException e) {
						sender.sendMessage("§c[!] Digite um numero valido!");
						return true;
					}
					if (jogador == null) {
						p.sendMessage("§c[!] Jogador não encontrado!");
						return false;
					}
					if (quantia < 0) {
						p.sendMessage(
								"§c[!] O Jogador já está com a quantia de 0 coins não e possível setar um quantia negativa!");
						return false;
					}
					MetodosEconomy.setar(jogador.getName(), quantia);
					p.sendMessage("§aVocê setou §f" + MetodosEconomy.format(quantia.longValue()) + "§a coins na conta §f"
							+ jogador.getName() + "§a com sucesso!");

					return false;
				}

				if (args[0].equalsIgnoreCase("remover")) {

					if (args.length <= 2) {
						p.sendMessage("§c[!] Use: /money remover <jogador> <quantia>.");
						return false;
					}
					Double quantia;
					try {

						quantia = Double.valueOf(args[2]);
					} catch (NumberFormatException e) {
						sender.sendMessage("§c[!] Digite um numero valido!");
						return true;
					}
					Player jogador = Bukkit.getPlayerExact(args[1]);

					if (jogador == null) {
						p.sendMessage("§c[!] Jogador não encontrado!");
						return false;
					}
					if (quantia > MetodosEconomy.PegarMoney(p.getName())) {
						p.sendMessage(
								"§c[!] Não e possivel remover essa quantia da conta do jogador.Pois e quantia e maior que a que o jogador possue!");
						return false;
					}
					MetodosEconomy.remove(jogador.getName(), quantia);
					p.sendMessage("§aVocê remover §f" + MetodosEconomy.format(quantia.longValue()) + "§a coins da conta §f"
							+ jogador.getName() + "§a com sucesso!");
					return false;
				}

				if (args[0].equalsIgnoreCase("adicionar")) {

					if (args.length <= 2) {
						p.sendMessage("§c[!] Use: /money adicionar <jogador> <quantia>.");
						return false;
					}
					Player jogador = Bukkit.getPlayerExact(args[1]);
					Double quantia;
					try {
						quantia = Double.valueOf(args[2]);
					} catch (NumberFormatException e) {
						sender.sendMessage("§c[!] Digite um numero valido!");
						return true;
					}
					if (jogador == null) {
						p.sendMessage("§c[!] Jogador não encontrado!");
						return false;
					}
					if (quantia < 0) {
						p.sendMessage(
								"§c[!] O Jogador já está com a quantia de 0 coins não e possível setar um quantia negativa!");
						return false;
					}
					MetodosEconomy.adicionar(jogador.getName(), quantia);
					p.sendMessage("§aVocê adicionou  §f" + MetodosEconomy.format(quantia.longValue()) + "§a coins da conta §f"
							+ jogador.getName() + "§a com sucesso!");
					return false;
				}
			}
			return false;
		}
		return false;
	}

}
