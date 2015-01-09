package br.com.stefani.ivan.amiltest.model;

import java.util.Date;

import br.com.stefani.ivan.amiltest.util.EnumDeathType;

public class Death {

	private Match match;
	private Player playerKiller;
	private Player playerKilled;
	private EnumDeathType deathType;
	private Date timeDeath;

	public Death(Match match, Player playerKiller, Player playerKilled,
			EnumDeathType deathType, Date timeDeath) {
		super();
		this.match = match;
		this.playerKiller = playerKiller;
		this.playerKilled = playerKilled;
		this.deathType = deathType;
		this.timeDeath = timeDeath;
	}

	public Player getPlayerKiller() {
		return playerKiller;
	}

	public void setPlayerKiller(Player playerKiller) {
		this.playerKiller = playerKiller;
	}

	public Player getPlayerKilled() {
		return playerKilled;
	}

	public void setPlayerKilled(Player playerKilled) {
		this.playerKilled = playerKilled;
	}

	public Date getTimeDeath() {
		return timeDeath;
	}

	public void setTimeDeath(Date timeDeath) {
		this.timeDeath = timeDeath;
	}

	public EnumDeathType getDeathType() {
		return deathType;
	}

	public void setDeathType(EnumDeathType deathType) {
		this.deathType = deathType;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	@Override
	public String toString() {
		return "Death: idMatch=" + this.match.getIdMatch() + " playerKiller="
				+ this.playerKiller.getIdStrPlayer() + " playerKilled="
				+ this.playerKilled.getIdStrPlayer() + " deathType="
				+ this.deathType.toString() + " timeDeath=" + this.timeDeath;
	}
}
