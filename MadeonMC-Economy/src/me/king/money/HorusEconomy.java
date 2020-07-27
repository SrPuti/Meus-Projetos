package me.king.money;

import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.Map.Entry;



public class HorusEconomy  {
	
	public static final NavigableMap<Long, String> suffixes = new TreeMap<>();


	public static Double SetarMoney(String jogador, Double money) {
		MetodosEconomy.setar(jogador, money);
		return money;
	}

	public static  Double RemoverMoney(String jogador, Double money) {
		MetodosEconomy.remove(jogador, money);
		return money;
	}
	
	public static  Double adicionarMoney(String jogador, Double money) {
		MetodosEconomy.adicionar(jogador, money);
		return money;
	}
	
	public static List<String> TopList() {
		return MetodosEconomy.GetMoneyTop();
	}

	public static  Double Enviar(String jogador, String JogadorReceber, Double money) {
		if (!MetodosEconomy.contains(jogador)) {
			MetodosEconomy.setPlayer(jogador);
		}else {
		MetodosEconomy.adicionar(JogadorReceber, money);
		MetodosEconomy.remove(jogador, money);
		}
		return money;
	}

	public static Double getMoney(String jogador) {
		if (!MetodosEconomy.contains(jogador)) {
			MetodosEconomy.setPlayer(jogador);
			return 0.0;
		}else {
		return MetodosEconomy.PegarMoney(jogador);

	}
	}
	public void setarPlayer(String jogador) {
			MetodosEconomy.setPlayer(jogador);
		
	}
	
	public static String  getmagnata(){
		return MetodosEconomy.Maganta();
	}
	
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



}
