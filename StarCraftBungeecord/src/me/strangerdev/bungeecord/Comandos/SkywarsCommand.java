package me.strangerdev.bungeecord.Comandos;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import me.strangerdev.bungeecord.Main;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class SkywarsCommand extends Command{

	public static ArrayList<ProxiedPlayer> connect = new ArrayList<ProxiedPlayer>();

	public SkywarsCommand(){
		super("skywars");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if (connect.contains(p)) {
				p.sendMessage(new TextComponent("§c Você já está na fila."));
				return;
			}else {
				
				connect.add(p);
				for (int i = 0;i < connect.size();i++) {
					ProxiedPlayer rs = connect.get(i);
					if (rs.getDisplayName().equals(p.getDisplayName())) {
						if (connect.size() == 1) {
							if (connect.get(0).equals(p)) {
							try {
								p.connect(BungeeCord.getInstance().getServerInfo("SkyWars"));
								p.sendMessage(new TextComponent("§a Enviado para o servidor 'Sky Wars' com sucesso."));
							} catch (Exception e) {
								p.sendMessage(new TextComponent("§c Não foi possível conectar ao servidor."));
							}
							connect.remove(p);
						}
						}else {
						p.sendMessage(new TextComponent("§e Você está em §f#"+i+"§e lugar na fila do Sky Wars."));
						}
					}
				}
			for (int i = 0; i< connect.size();i++) {
				Main.plugin.getProxy().getScheduler().schedule(Main.plugin, new Runnable() {
					
					@Override
					public void run() {
						ProxiedPlayer proximo = connect.get(0);
						if (proximo != null) {
						try {
							proximo.connect(BungeeCord.getInstance().getServerInfo("SkyWars"));
							p.sendMessage(new TextComponent("§a Enviado para o servidor 'Sky Wars' com sucesso."));
						} catch (Exception e) {
							p.sendMessage(new TextComponent("§c Não foi possível conectar ao servidor."));
						}
						connect.remove(proximo);
						}
					}
				}, 30, TimeUnit.SECONDS);
			}
			}
			
		}
		
	}
}

