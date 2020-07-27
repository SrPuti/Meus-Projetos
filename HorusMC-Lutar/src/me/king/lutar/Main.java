package me.king.lutar;

import org.bukkit.plugin.java.JavaPlugin;

import me.king.lutar.Comandos.ComandosAdmin;
import me.king.lutar.Comandos.ComandosMembro;
import me.king.lutar.Comandos.MitoComando;
import me.king.lutar.Eventos.Eventos;

public class Main extends JavaPlugin{

	public static Main getPlugin() {
		return getPlugin(Main.class);
	}
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		getServer().getConsoleSender().sendMessage("§aPlugin Ativo!");
		getServer().getPluginManager().registerEvents(new Eventos(), this);
		getCommand("x1").setExecutor(new ComandosMembro());
		getCommand("x1admin").setExecutor(new ComandosAdmin());
		getCommand("mito").setExecutor(new MitoComando());

	}
	
	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage("§cPlugin Desativado!");

	
	}
	
	
	
}
