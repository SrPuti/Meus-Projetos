package me.strangerdev.bungeecord.Comandos;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class LobbyComand extends Command {

	public LobbyComand() {
		super("lobby");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			sender.sendMessage(new TextComponent("§a Enviado para o Lobby com sucesso."));
			p.connect(BungeeCord.getInstance().getServerInfo("Lobby"));
			
		}
		
	}

}
