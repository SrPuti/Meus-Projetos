package me.king.lutar.Comandos;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.king.Clans.Clan;
import me.king.Principal.RankupAPI;
import me.king.lutar.Main;
import me.king.lutar.API.API;
import me.king.lutar.API.Criar;
import me.king.lutar.API.K_Actionbar;
import me.king.money.HorusEconomy;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class ComandosMembro implements CommandExecutor {

	public static ArrayList<Player> desafiar = new ArrayList<Player>();
	public static ArrayList<Player> emX1 = new ArrayList<Player>();
	public static ArrayList<Player> x1 = new ArrayList<Player>();
	public static ArrayList<Player> x1_delay = new ArrayList<Player>();

	public RankupAPI rank;

	@SuppressWarnings({ "static-access", "deprecation" })
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage("");
			return false;
		}
		if (cmd.getName().equalsIgnoreCase("x1")) {
			Player p = (Player) sender;
			if (args.length == 0) {
				p.sendMessage("§7Lista de comandos:");
				p.sendMessage("");
				p.sendMessage("§a/x1 desafiar <jogador> §7- Desafiar o jogador.");
				p.sendMessage("§a/x1 aceitar <jogador> §7- Aceitar um desafio.");
				p.sendMessage("§a/x1 negar <jogador> §7- Negar um desafio.");
				p.sendMessage("§a/x1 info <jogador> §7- Informações sobre o jogador.");
				return false;
			}
			if (args[0].equalsIgnoreCase("desafiar")) {
				if (args.length == 1) {
					p.sendMessage("§a/x1 desafiar <jogador>.");
					return false;
				}
				if (!x1_delay.contains(p)) {
					Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.getPlugin(), new Runnable() {
						public void run() {

							Player jogador = Bukkit.getPlayerExact(args[1]);
							if (jogador == null) {
								p.sendMessage("§c[!] Jogador não encontrado!");
							}
							if (jogador.getName() == p.getName()) {
								p.sendMessage("§c[!] O Remetente não Pode ser você !");
							}
							if (x1.contains(p)) {
								p.sendMessage("§c[!] Aguarde outro x1 em andamento!");
							}
							if (emX1.contains(jogador)) {
								p.sendMessage("§c[!] O Jogador está em outro X1");
							}
							p.sendMessage("§aVocê desafiou o jogador §7'" + jogador.getName() + "'§a com sucesso!");
							jogador.sendMessage("§aYOU Você Foi Desafiado!");
							jogador.sendMessage("");
							jogador.sendMessage("§aO Jogador §7'" + p.getName() + "'§a Está te chamando para a mão!");
							jogador.sendMessage("");
							emX1.add(p);
							x1_delay.add(p);
						}
					}, 2 * 20L);
				} else {
					p.sendMessage("§c[!] Aguarde antes de executar o comando novamente!");
					x1_delay.remove(p);
				}
				return false;
			}

			if (args[0].equalsIgnoreCase("info")) {
				if (args.length == 1) {
					p.sendMessage("§a/x1 info <jogador>.");
					return false;
				}
				Player jogador = Bukkit.getPlayerExact(args[1]);
				if (jogador == null) {
					p.sendMessage("§c[!] Jogador não encontrado!");
					return false;
				}
				String Grupo = PermissionsEx.getUser(jogador).getPrefix().replace("&", "§");
				String magnata = HorusEconomy.getmagnata();
				String TGMT;
				String clanTG;
				Clan clan = rank.getClan(p);
				if (clan == null) {
					clanTG = "";
				} else {
					clanTG = "[" + rank.getClan(p).getTagColorida().replace("&", "§") + "]";
				}
				if (!magnata.equalsIgnoreCase(p.getName())) {
					TGMT = "§cNão e o Magnata!";
				} else {
					TGMT = "§a[$]";
				}

				p.sendMessage("§7Informações:");
				p.sendMessage("§f");
				p.sendMessage("§aNick: §7" + jogador.getName());
				p.sendMessage("§aGrupo: §7" + Grupo);
				p.sendMessage("§aMagnata: §7" + TGMT);
				p.sendMessage("§aClã: §7" + clanTG);
				p.sendMessage("§f");

				return false;
			}

			if (args[0].equalsIgnoreCase("aceitar")) {
				if (args.length == 1) {
					p.sendMessage("§a/x1 aceitar <jogador>.");
					return false;
				}
				Player jogador = Bukkit.getPlayerExact(args[1]);
				if (jogador == null) {
					p.sendMessage("§c[!] Jogador não encontrado!");
					return false;
				}
				if (!emX1.contains(jogador)) {
					p.sendMessage("§c[!] Sem solicitação!");
					return false;
				}
				if (p.getInventory().getContents() == null && jogador.getInventory().getContents() == null) {
					p.sendMessage("§c[!] Para aceitar o X1 e preciso está com o inventário vázio!");
					return false;
				}
				emX1.add(jogador);
				x1.add(p);
				K_Actionbar.EnviarMenssagem(p, "§c[!] Luta iniciada!");
				K_Actionbar.EnviarMenssagem(jogador, "§c[!] Luta iniciada!");
				Bukkit.broadcastMessage("§a[X1]§7 O Jogador §f" + p.getName() + "§a está lutando com o jogador §f"
						+ jogador.getName() + "§a na arena X1!");
				API.Teleporte(p, "local1");
				API.Teleporte(jogador, "local2");
				jogador.getInventory().setChestplate(new ItemStack(Criar.add(Material.LEATHER_CHESTPLATE, "§cEm X1")));
				jogador.getInventory().setLeggings(new ItemStack(Criar.add(Material.LEATHER_LEGGINGS, "§cEm X1")));
				jogador.getInventory().setBoots(new ItemStack(Criar.add(Material.LEATHER_BOOTS, "§cEm X1")));
				jogador.getInventory().addItem(new ItemStack(Criar.add(Material.DIAMOND_SWORD)));
				jogador.updateInventory();

				p.getInventory().setHelmet(new ItemStack(Criar.add(Material.LEATHER_HELMET, "§cEm X1")));
				p.getInventory().setChestplate(new ItemStack(Criar.add(Material.LEATHER_CHESTPLATE, "§cEm X1")));
				p.getInventory().setLeggings(new ItemStack(Criar.add(Material.LEATHER_LEGGINGS, "§cEm X1")));
				p.getInventory().setBoots(new ItemStack(Criar.add(Material.LEATHER_BOOTS, "§cEm X1")));
				p.getInventory().addItem(new ItemStack(Criar.add(Material.DIAMOND_SWORD)));
				p.updateInventory();

				return false;
			}
			if (args[0].equalsIgnoreCase("negar")) {
				if (args.length == 1) {
					p.sendMessage("§a/x1 negar <jogador>.");
					return false;
				}
				Player jogador = Bukkit.getPlayerExact(args[1]);
				if (jogador == null) {
					p.sendMessage("§c[!] Jogador não encontrado!");
					return false;
				}
				if (emX1.contains(jogador)) {
					p.sendMessage("§c[!] Este Jogador está batalhando agora!");
					return false;
				}
				p.sendMessage("§c[!] Pedido negado com sucesso!!");
				jogador.sendMessage("§c[!] O Jogador §f" + p.getName() + "§c negou o seu pedido!");
				return false;
			}

			return false;
		}

		return false;
	}

}
