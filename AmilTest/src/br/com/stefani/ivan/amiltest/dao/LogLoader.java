package br.com.stefani.ivan.amiltest.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.stefani.ivan.amiltest.model.Death;
import br.com.stefani.ivan.amiltest.model.Match;
import br.com.stefani.ivan.amiltest.model.Player;
import br.com.stefani.ivan.amiltest.util.EnumDeathType;
import br.com.stefani.ivan.amiltest.util.EnumLineType;

public class LogLoader {
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public List<Match> loadLogFile(String fileName) throws IOException,
			CloneNotSupportedException {
		List<Match> matchList = new ArrayList<Match>();
		File file = new File(fileName);
		FileInputStream fis = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String line = null;

		Match match = null;
		while ((line = br.readLine()) != null) {
			if (this.identifyLineType(line).equals(EnumLineType.MATCH_START)
					&& match == null) {
				match = this.parseMatchStart(line);
			}

			if (identifyLineType(line).equals(EnumLineType.DEATH)
					&& match != null) {
				match = this.parseDeath(line, match);
			}

			if (identifyLineType(line).equals(EnumLineType.MATCH_END)
					&& match != null) {

				match = this.parseMatchEnd(line, match);

				// resetting pointer
				matchList.add((Match) match.clone());
				match = null;
			}
		}

		br.close();
		return matchList;
	}

	private EnumLineType identifyLineType(String line) {
		if (line != null && !line.isEmpty()) {
			if (line.toUpperCase().contains("MATCH")) {
				if (line.toUpperCase().contains("NEW")) {
					return EnumLineType.MATCH_START;
				}
				if (line.toUpperCase().contains("ENDED")) {
					return EnumLineType.MATCH_END;
				}
			}

			if (line.toUpperCase().contains("KILLED")) {
				return EnumLineType.DEATH;
			}
		}
		return EnumLineType.ERROR;
	}

	private Match parseMatchStart(String line) {
		int idMatch = this.getLineIdMatch(line);
		Date matchStart = this.getLineDate(line);
		return new Match(idMatch, matchStart);
	}

	private Player addOrUpdateNewPlayers(String playerStr, Match match) {
		if (match.getPlayerMap().containsKey(playerStr)){
			return match.getPlayerMap().get(playerStr);
		} else {
			Player player = new Player(playerStr);
			match.getPlayerMap().put(player.getIdStrPlayer(), player);
			return player;
		}
			
			
	}

	private Match parseDeath(String line, Match match) {
		String playerKillerStr = this.getLinePlayerKiller(line);
		String playerKilledStr = this.getLinePlayerKilled(line);

		Player playerKiller = this.addOrUpdateNewPlayers(playerKillerStr, match);
		Player playerKilled = this.addOrUpdateNewPlayers(playerKilledStr, match);

		EnumDeathType deathType = this.getLineDeathType(line);
		Date timeDeath = this.getLineDate(line);
		Death death = new Death(match, playerKiller, playerKilled, deathType,
				timeDeath);
		match.getDeathList().add(death);
		playerKiller.getKillList().add(death);
		playerKilled.getDeathList().add(death);
		return match;
	}

	private Match parseMatchEnd(String line, Match match) {
		match.setEnd(this.getLineDate(line));
		return match;
	}

	private Date getLineDate(String line) {
		Date date = null;

		try {
			date = formatter.parse(line.split("-")[0].trim());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	private Integer getLineIdMatch(String line) {
		Pattern pattern = Pattern.compile("(?<=match.)\\S+\\S");
		Matcher matcher = pattern.matcher(line);
		if (matcher.find()) {
			return Integer.valueOf(matcher.group(0));
		}
		return null;
	}

	private String getLinePlayerKiller(String line) {
		return line.split("killed")[0].split("-")[1].trim();
	}

	private String getLinePlayerKilled(String line) {
		Pattern pattern = Pattern.compile("(?<=killed.)\\S+\\S");
		Matcher matcher = pattern.matcher(line);
		if (matcher.find()) {
			return matcher.group(0);
		}
		return null;
	}

	private EnumDeathType getLineDeathType(String line) {
		Pattern pattern = Pattern.compile("\\s(\\w+)$");
		Matcher matcher = pattern.matcher(line);
		if (matcher.find()) {
			return EnumDeathType.valueOf(matcher.group(0).trim());
		}
		return null;
	}

}
