package br.com.stefani.ivan.amiltest.util;

import java.util.List;

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
				System.out.println("--------- Jogador: " + player.getIdStrPlayer() );
				System.out.println("------------ Assassinatos: " + player.getKillList().size() );
				System.out.println("------------ Mortes: " + player.getDeathList().size() );
			}
		}
	}

}
