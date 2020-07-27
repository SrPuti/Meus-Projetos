package me.king.money;



import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.king.money.api.K_Actionbar;
import me.king.money.comandos.MagnataComando;
import me.king.money.comandos.MoneyComando;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Main extends JavaPlugin implements Listener{
	
	   public static Main instance;
	   public HorusEconomy economy;
	   

	   
	   public static Main getPlugin() {
		return getPlugin(Main.class);
	}
	
	
	
	@Override
	public void onEnable() {
		Conexao.open();
		Conexao.createTable();
		economy = new HorusEconomy(); 

		instance = this;

		Bukkit.getConsoleSender().sendMessage("§aPlugin §cHorusEconomy §acarregado com sucesso!");
		Comandos();
		Eventos();
		
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("§aPlugin §cHorusEconomy §adescarregado com sucesso!");
		Conexao.close();
	}
	
	
	private void Comandos() {

		getCommand("money").setExecutor(new MoneyComando());
		getCommand("magnata").setExecutor(new MagnataComando());
		Bukkit.getConsoleSender().sendMessage("§a[!] Comandos Carregados com sucesso!");
	}
	
	
	private void Eventos() {
		getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getConsoleSender().sendMessage("§a[!] Eventos Carregados com sucesso!");
	}
	
	@EventHandler
	public void Magnata() {
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
			public void run() {
				for (Player all : Bukkit.getOnlinePlayers()) {
					if (MetodosEconomy.Maganta() == null) {
						
					}
				K_Actionbar.EnviarMenssagem(all, "§a[MAGNATA]§e YOW Temos um novo magnata o jogador §f"+ MetodosEconomy.Maganta() + "§e.Parabéns!");
				String p = HorusEconomy.getmagnata();
				all.sendMessage("§a[Novo Magnata]:");
				all.sendMessage("§eNick: §7" + HorusEconomy.getmagnata());
				all.sendMessage("§eGrupo: §7"+ PermissionsEx.getUser(p).getPrefix().replace("&", "§"));
				all.sendMessage("");
				all.playSound(all.getLocation(), Sound.ENDERDRAGON_GROWL, 0.5F, 0.5F);
				}
				
			}
		}, 10*20L);
	}
	
	
	
}
