package br.com.stefani.ivan.amiltest.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private String idStrPlayer;

	private List<Death> killList = new ArrayList<Death>();
	private List<Death> deathList = new ArrayList<Death>();

	public Player(String idStrPlayer) {
		this.idStrPlayer = idStrPlayer;
	}

	public String getIdStrPlayer() {
		return idStrPlayer;
	}

	public void setIdStrPlayer(String idStrPlayer) {
		this.idStrPlayer = idStrPlayer;
	}

	public List<Death> getKillList() {
		return killList;
	}

	public void setKillList(List<Death> killList) {
		this.killList = killList;
	}

	public List<Death> getDeathList() {
		return deathList;
	}

	public void setDeathList(List<Death> deathList) {
		this.deathList = deathList;
	}

	@Override
	public String toString() {
		return "Player: id=" + this.getIdStrPlayer();
	}
}
