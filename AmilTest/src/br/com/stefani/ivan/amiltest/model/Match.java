package br.com.stefani.ivan.amiltest.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Match implements Cloneable {
	private int idMatch;
	private Date start;
	private Date end;
	private HashMap<String, Player> playerMap;
	private List<Death> deathList;

	public Match(int idMatch, Date start) {
		super();
		this.idMatch = idMatch;
		this.start = start;
		this.playerMap = new LinkedHashMap<String, Player>();
		this.deathList = new ArrayList<Death>();
	}

	public int getIdMatch() {
		return idMatch;
	}

	public void setIdMatch(int idMatch) {
		this.idMatch = idMatch;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public HashMap<String, Player> getPlayerMap() {
		return playerMap;
	}

	public void setPlayerMap(HashMap<String, Player> playerMap) {
		this.playerMap = playerMap;
	}

	public List<Death> getDeathList() {
		return deathList;
	}

	public void setDeathList(List<Death> deathList) {
		this.deathList = deathList;
	}

	@Override
	public String toString() {
		return "Match: id=" + this.getIdMatch() + " start=" + this.getStart()
				+ " end=" + this.getEnd();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
