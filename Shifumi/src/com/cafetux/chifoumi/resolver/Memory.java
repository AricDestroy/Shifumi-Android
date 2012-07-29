package com.cafetux.chifoumi.resolver;

import java.util.ArrayList;
import java.util.List;

import com.cafetux.chifoumi.model.AttackTypes;

public class Memory {

	private static final List<AttackTypes> previousAttacks = new ArrayList<AttackTypes>();
	private static final List<Boolean> previousPlayerWin = new ArrayList<Boolean>();

	public static void addResult(AttackTypes attack, Boolean playerWin) {
		previousAttacks.add(attack);
		previousPlayerWin.add(playerWin);
	}

	public static void reset() {
		previousAttacks.clear();
		previousPlayerWin.clear();
	}

	public static AttackTypes getPreviousAttack(int nbBack) {
		if (previousAttacks.size() == 0 || nbBack < 1) {
			return null;
		}
		return previousAttacks.get(previousAttacks.size() - (nbBack + 1));
	}

	public static AttackTypes getPreviousAttack() {
		return getPreviousAttack(previousAttacks.size() - 1);
	}

	public static Boolean playerWinPreviously() {
		if (previousPlayerWin.size() == 0) {
			return false;
		}
		return previousPlayerWin.get(previousPlayerWin.size() - 1);
	}
}