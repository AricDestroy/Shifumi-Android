package com.cafetux.chifoumi.resolver;

import java.util.ArrayList;
import java.util.List;

import com.cafetux.chifoumi.model.AttackTypes;

public class Memory {

	private static final List<AttackTypes> previousAttacks = new ArrayList<AttackTypes>();
	private static final List<Boolean> previousPlayerWin = new ArrayList<Boolean>();
	private static int nbWin = 0;
	private static int nbDraw = 0;
	private static int consecutivesWin = 0;
	private static int nbPaper = 0;
	private static int nbStone = 0;
	private static int nbScissor = 0;

	public static void addResult(AttackTypes attack, Boolean playerWin) {
		previousAttacks.add(attack);
		previousPlayerWin.add(playerWin);
		incrementVictoryStats(playerWin);
		incrementAttackTypeStat(attack);
	}

	private static void incrementAttackTypeStat(AttackTypes attack) {
		switch (attack) {
		case PAPER:
			nbPaper++;
			break;
		case STONE:
			nbStone++;
			break;
		case SCISSOR:
			nbScissor++;
			break;
		}
	}

	private static void incrementVictoryStats(Boolean playerWin) {
		if (playerWin == null) {
			nbDraw++;
		} else if (playerWin) {
			consecutivesWin++;
			nbWin++;
		} else {
			consecutivesWin = 0;
		}
	}

	public static void reset() {
		previousAttacks.clear();
		previousPlayerWin.clear();
		nbWin = 0;
		nbDraw = 0;
		nbPaper = 0;
		nbScissor = 0;
		nbStone = 0;
	}

	public static AttackTypes getPreviousAttack(int nbBack) {
		int indexToGet = previousAttacks.size() - (nbBack + 1);
		// Log.d("Memory", "getIndex:" + indexToGet + "/" +
		// previousAttacks.size());

		if (previousAttacks.size() == 0 || indexToGet < 0) {
			return null;
		}
		return previousAttacks.get(indexToGet);
	}

	public static AttackTypes getPreviousAttack() {
		return getPreviousAttack(0);
	}

	public static Boolean playerWinPreviously() {
		if (previousPlayerWin.size() == 0) {
			return false;
		}
		return previousPlayerWin.get(previousPlayerWin.size() - 1);
	}

	public static int getNbWin() {
		return nbWin;
	}

	public static int getNbDraw() {
		return nbDraw;
	}

	public static int getNbGames() {

		return previousAttacks.size();
	}

	public static AttackTypes getMostAttackChoiced() {

		if (nbPaper > nbStone) {
			if (nbPaper > nbScissor) {
				return AttackTypes.PAPER;
			} else {
				return AttackTypes.SCISSOR;
			}
		} else {
			if (nbScissor > nbStone) {
				return AttackTypes.SCISSOR;
			} else {
				return AttackTypes.STONE;
			}
		}
	}

	public static int getNbConsecutiveWin() {

		return consecutivesWin;
	}
}