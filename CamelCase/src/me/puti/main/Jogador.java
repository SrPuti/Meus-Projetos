package me.puti.main;

import org.bukkit.entity.Player;

public class Jogador {

	public String nome;
	public double maxHealth;
	
	
	public Jogador(Player p) {
		this.nome = p.getName();
		this.maxHealth = p.getMaxHealth();
		
	
	}
	
	public String getName() {
		return this.nome;
	}
	
	public double getMaxHealth() {
		return this.maxHealth;
	}
	
	
	public void setName(String nome) {
		this.nome = nome;
	}
	
	
}
