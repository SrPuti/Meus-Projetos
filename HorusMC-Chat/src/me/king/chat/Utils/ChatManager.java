package me.king.chat.Utils;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import me.king.Principal.RankupAPI;
import me.king.money.HorusEconomy;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class ChatManager {

	private static RankupAPI rank;
	public static ArrayList<Player> telloff = new ArrayList<>();
	public static ArrayList<Player> mutar = new ArrayList<>();

	public static String getGroup(Player p) {
		String group = PermissionsEx.getUser(p).getPrefix().replace("&", "§");
		return group;
	}
	@SuppressWarnings("static-access")
	public static String getrank(Player p) {
		String Rank;
		if (rank.getTag(p).equals("§7[Sem Rank]")) {
			Rank = "";
		}else {
			Rank = rank.getTag(p);
		}
		return Rank;
}
	public static String getMagnata(Player p) {
		String magnata = HorusEconomy.getmagnata();
		String Tag;
		if (!magnata.equalsIgnoreCase(p.getName())) {
			Tag = "";
		}else {
			Tag = "§a[$]§7";
		}
		return Tag;
	}
	@SuppressWarnings("static-access")
	public static String getclanTag(Player p) {
		String Tag;
		if (rank.getClan(p) == null) {
			Tag= "";
		}else {
			Tag = "§7["+rank.getClan(p).getTagColorida().replace("&", "§")+ "§7]§7";
		}
		return Tag;
	}
	
	public static void desativar(Player p) {
		if (!telloff.contains(p)) {
			telloff.add(p);
			p.sendMessage("§aTell desativado!");
		}
	}

	public static void ativar(Player p) {
		if (telloff.contains(p)) {
			telloff.remove(p);
			p.sendMessage("§aTell Ativado");
		}
	}

    public static void Mutar(Player p) {
	if(!mutar.contains(p)) {
		mutar.add(p);
		p.sendMessage("§c[!] Jogador Mutado com sucesso!");
	}	
	}

	public static void desMutar(Player p) {
		if(mutar.contains(p)) {
			mutar.remove(p);
			p.sendMessage("§c[!] Jogador desmutado com sucesso!");
		}
		}
}
