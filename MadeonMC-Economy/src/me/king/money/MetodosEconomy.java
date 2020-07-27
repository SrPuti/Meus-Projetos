package me.king.money;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class MetodosEconomy extends Conexao {


	public static String prefix = "§6[§bMYSQL§6] §f> ";
	public static ConsoleCommandSender sc = Bukkit.getConsoleSender();
	public static final NavigableMap<Long, String> suffixes = new TreeMap<>();

	
	public static String format(long Double) {
		suffixes.put(1_000L, " K");
		suffixes.put(1_000_000L, " KK");
		suffixes.put(1_000_000_000L, " B");
		suffixes.put(1_000_000_000_000L, " T");
		suffixes.put(1_000_000_000_000_000L, " Q");
		suffixes.put(1_000_000_000_000_000_000L, " QQ");
		suffixes.put((long) 1_000_000_000_000_000_000_000F, "S");
		if (Double  == Long.MIN_VALUE)
			return format(Long.MIN_VALUE + 1);
		if (Double  < 0)
			return "-" + format(-Double);
		if (Double < 1000)
			return Long.toString(Double);

		Entry<Long, String> e = suffixes.floorEntry(Double );
		Long divideBy = e.getKey();
		String suffix = e.getValue();

		long truncated = Double  / (divideBy / 10);
		boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
		return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
	}
	
	
	public static boolean contains(String player) {
		PreparedStatement stm = null;
		try {
			stm = con.prepareStatement("SELECT * FROM `money` WHERE `player` = ?");
			stm.setString(1, player.toLowerCase());
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			return false;
		}
	}

	public static void setPlayer(String player) {
		PreparedStatement stm = null;
		try {
			stm = con.prepareStatement("INSERT INTO `money`(`player`, `quantia`) VALUES (?,?)");
			stm.setString(1, player.toLowerCase());
			stm.setDouble(2, 0);
			stm.executeUpdate();
			sc.sendMessage(prefix + "§aPlayer §f" + player + "§a criado com sucesso!");
		} catch (SQLException e) {
			sc.sendMessage(prefix + "§cNão foi possivel inserir o player: §f" + player + "§a no banco de dados!");
		}
	}

	public static void setar(String player, Double quantia) {
		if (contains(player)) {
			PreparedStatement stm = null;
			try {
				stm = con.prepareStatement("UPDATE `money` SET `quantia` = ? WHERE `player` = ?");
				stm.setDouble(1, quantia);
				stm.setString(2, player.toLowerCase());
				stm.executeUpdate();
			} catch (SQLException e) {
				sc.sendMessage(MetodosEconomy.prefix + "§cNão foi possivel setar o cash do jogador");
			}
		} else {
			setPlayer(player);
		}
	}

	public static Double PegarMoney(String player) {
		if (contains(player)) {
			PreparedStatement stm = null;
			try {
				stm = con.prepareStatement("SELECT * FROM `money` WHERE `player` = ?");
				stm.setString(1, player.toLowerCase());
				ResultSet rs = stm.executeQuery();
				while (rs.next()) {
					return rs.getDouble("quantia");
				}
				return 0.0;
			} catch (SQLException e) {
				return 0.0;
			}
		} else {
			setPlayer(player);
			return 0.0;
		}
	}

	public static void adicionar(String player, Double quantia) {
		if (contains(player)) {
			setar(player, PegarMoney(player) + quantia);
		} else {
			setPlayer(player);
		}
	}
	
	

	public static void remove(String player, Double quantia) {
		if (contains(player)) {
			setar(player, PegarMoney(player) - quantia);
		} else {
			setPlayer(player);
		}
	}

	public static void delete(String player) {
		if (contains(player)) {
			PreparedStatement stm = null;
			try {
				stm = con.prepareStatement("DELETE FROM `money` WHERE `player` = ?");
				stm.setString(1, player.toLowerCase());
				stm.executeUpdate();
			} catch (SQLException e) {
				sc.sendMessage(
						MetodosEconomy.prefix + "§cNão foi possivel remover o jogador §f" + player + "§c do banco de dados!");
			}
		}
	}

	public static List<String> GetMoneyTop() {
		PreparedStatement stm = null;
		List<String> tops = new ArrayList<String>();
		try {
			stm = con.prepareStatement("SELECT * FROM `money` ORDER BY `quantia` DESC");
			ResultSet rs = stm.executeQuery();
			int i = 0;
			while (rs.next()) {
				if (i <= 10) {
					i++;
					String player = rs.getString("player");
					String group = PermissionsEx.getUser(player).getPrefix().replace("&", "§");
					double din = rs.getDouble("quantia");
					tops.add("§e" + i + "º §7" + group + " "+ rs.getString("player") + " §c- §7(" + format((long) din) + " de coins)");
				}
			}
		} catch (SQLException e) {
			sc.sendMessage(MetodosEconomy.prefix + "§cNão foi possivel carregar o top money");
		}
		return tops;
	}
	
	public static String Maganta(){
		
		PreparedStatement stm = null;
		String magnata = "Nenhum";
		try {
			stm = con.prepareStatement("SELECT * FROM `money` ORDER BY `quantia` DESC");
			ResultSet rs = stm.executeQuery();
			int i = 0;
			while (rs.next()) {
				if (i < 1) {
					i++;
					magnata = rs.getString("player");
				}
			}
		} catch (SQLException e) {
			sc.sendMessage(MetodosEconomy.prefix + "§cNão foi possivel pegar o magnata!");
		}
		return magnata;
	}


}
