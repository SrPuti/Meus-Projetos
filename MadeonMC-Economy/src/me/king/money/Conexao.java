package me.king.money;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

public class Conexao {

	public static Connection con = null;
	public static ConsoleCommandSender sc = Bukkit.getConsoleSender();

	/*
	 * 
	 String url = "jdbc:mysql://localhost:3306/madara.nobori@gmail.com";
	 String user = "madara.nobori@gmail.com";
	 String password = "2897asXa";
		
	 * 
	 */
	public static void open() {
		String url = "jdbc:mysql://localhost:3306/king_servidoresteste";
		String user = "root";
		String password = "";
		try {
			con = DriverManager.getConnection(url, user, password);
			sc.sendMessage(MetodosEconomy.prefix + "�aConex�o com MySQL aberta!");
		} catch (SQLException e) {
			sc.sendMessage(MetodosEconomy.prefix + "�cConex�o com MySQL n�o foi possivel!");
			Main.getPlugin().getPluginLoader().disablePlugin(Main.getPlugin());
		}
	}

	public static void close() {
		if (con != null) {
			try {
				con.close();
				sc.sendMessage(MetodosEconomy.prefix + "�aConex�o fechada com sucesso!");
			} catch (SQLException e) {
				sc.sendMessage(MetodosEconomy.prefix + "�cN�o foi possivel fechar a conex�o!");
			}
		}
	}

	public static void createTable() {
		if (con != null) {
			PreparedStatement stm = null;
			try {
				stm = con.prepareStatement(
						"CREATE TABLE IF NOT EXISTS `money` (`id` INT NOT NULL AUTO_INCREMENT,`player` VARCHAR(24) NULL,`quantia` DOUBLE NULL,PRIMARY KEY (`id`));");
				stm.executeUpdate();
				sc.sendMessage(MetodosEconomy.prefix + "�aTabela carregad!a");
			} catch (SQLException e) {
				sc.sendMessage(MetodosEconomy.prefix + "�cN�o foi possivel carregar a tabela");
			}
		}
	}

}
