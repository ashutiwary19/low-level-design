package relationships;

import java.util.*;

class Player {
	String name;

	Player(String name) {
		this.name = name;
	}
}

class Team {
	String teamName;
	// Aggregation: A team "has" players.
	List<Player> players = new ArrayList<>();

	Team(String teamName) {
		this.teamName = teamName;
	}

	void addPlayer(Player player) {
		players.add(player);
	}

	void showTeam() {
		System.out.println("Team " + teamName + " has players:");
		for (Player p : players) {
			System.out.println(" - " + p.name);
		}
	}
}

public class Aggregation {
	public static void main(String[] args) {
		Team team = new Team("Warriors");
		team.addPlayer(new Player("Stephen"));
		team.addPlayer(new Player("Klay"));
		team.showTeam();
	}
}
