package br.com.stefani.ivan.amiltest.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import br.com.stefani.ivan.amiltest.model.Death;
import br.com.stefani.ivan.amiltest.model.Match;
import br.com.stefani.ivan.amiltest.model.Player;

public class ReportPrinter {
	List<Match> matchList;

	public ReportPrinter(List<Match> matchList) {
		this.matchList = matchList;
	}

	public void printReport() {
		System.out.println("--- Inicio do relatorio ---");
		for (Match match : this.matchList) {
			System.out.println("------ Partida: " + match.getIdMatch() );
			for (Player player : match.getPlayerMap().values()) {
				if( !player.getIdStrPlayer().toUpperCase().contains("WORLD") ){
					System.out.println("--------- Jogador: " + player.getIdStrPlayer() );
					System.out.println("------------ Assassinatos: " + player.getKillList().size() );
					System.out.println("------------ Mortes: " + player.getDeathList().size() );
					
					//Armas usadas
					LinkedHashMap<EnumDeathType, Integer> prefDeathType = new LinkedHashMap<EnumDeathType, Integer>();
					for(Death kill: player.getKillList()){
						 if( prefDeathType.containsKey(kill.getDeathType()) ){
							 Integer temp = prefDeathType.get(kill.getDeathType());
							 temp++;
						 } else {
							 prefDeathType.put(kill.getDeathType(), 1);
						 }
					}
					if( prefDeathType.size() > 0 ){
						System.out.println("------------ Armas Utilizadas: " );
						for(Entry<EnumDeathType, Integer> deathType : prefDeathType.entrySet()){
							System.out.println("--------------- " + deathType.getKey() + ": " +deathType.getValue() );
						}
					}
					
					
				}

			}
		}
	}
}