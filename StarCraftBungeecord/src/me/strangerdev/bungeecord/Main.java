package me.strangerdev.bungeecord;

import me.strangerdev.bungeecord.Comandos.BedWarsCommand;
import me.strangerdev.bungeecord.Comandos.LobbyComand;
import me.strangerdev.bungeecord.Comandos.MiniFullPvP;
import me.strangerdev.bungeecord.Comandos.SkywarsCommand;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {

	public static Main plugin;
	
	public void onEnable() {
		plugin = this;
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new LobbyComand());
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new BedWarsCommand());
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new SkywarsCommand());
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new MiniFullPvP());
		BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("§aPlugin ativado!"));
		BungeeCord.getInstance().getConsole().sendMessage(new TextComponent(""));
		BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("§7Bungeecord Utils.!"));
		BungeeCord.getInstance().getConsole().sendMessage(new TextComponent(""));

	}
	public void onDisable() {
		
		
		BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("§aPlugin desativado!"));
		BungeeCord.getInstance().getConsole().sendMessage(new TextComponent(""));
		BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("§7Bungeecord Utils.!"));
		BungeeCord.getInstance().getConsole().sendMessage(new TextComponent(""));

	}
}

