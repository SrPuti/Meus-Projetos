package me.king.chat;

import org.bukkit.plugin.java.JavaPlugin;

import me.king.chat.Comandos.ChatCommand;
import me.king.chat.Comandos.DesMutarCommand;
import me.king.chat.Comandos.GlobalCommand;
import me.king.chat.Comandos.LocalCommand;
import me.king.chat.Comandos.MutaCommand;
import me.king.chat.Comandos.TellCommand;
import me.king.chat.Comandos.ToggleTellCommand;
import me.king.chat.Eventos.ChatLocal;

public class Main extends JavaPlugin {

	public static Main getplugin;
	
	public static Main getplugin() {
		return getPlugin(Main.class);
	}
	
	@Override
	public void onEnable() {
	getServer().getConsoleSender().sendMessage("§aPlugin Ativo!");
	getServer().getPluginManager().registerEvents(new ChatLocal(), this);
	getCommand("chat").setExecutor(new ChatCommand());
	getCommand("toggletell").setExecutor(new ToggleTellCommand());
	getCommand("tell").setExecutor(new TellCommand());
	getCommand("g").setExecutor(new GlobalCommand());
	getCommand("mutar").setExecutor(new MutaCommand());
	getCommand("desmutar").setExecutor(new DesMutarCommand());
	getCommand("l").setExecutor(new LocalCommand());
	}
	
	
	@Override
	public void onDisable() {

		getServer().getConsoleSender().sendMessage("§cPlugin Desativado!");

	}
	
	
}
