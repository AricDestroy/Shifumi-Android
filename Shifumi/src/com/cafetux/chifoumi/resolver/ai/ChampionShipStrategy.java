package com.cafetux.chifoumi.resolver.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.util.Log;

import com.cafetux.chifoumi.model.AttackTypes;
import com.cafetux.chifoumi.resolver.Memory;
import com.cafetux.chifoumi.resolver.ShifumiMatchResolver.EnemyStrategy;

public class ChampionShipStrategy implements EnemyStrategy {

	private static boolean firstCall = true;
	private static final RandomStrategy randomStrategy = new RandomStrategy();
	private static final Random RANDOM = new Random();
	private static final List<AttackTypes> aiPreviousAttacks = new ArrayList<AttackTypes>();

	ChampionShipStrategy() {

	}

	@Override
	public AttackTypes getEnemyAttack() {
		if (firstCall) {
			Log.d("debug", "first call");
			firstCall = false;
			return returnValue(getFistAttack());
		}
		if (hasDoubleRock()) {
			return returnValue(AttackTypes.STONE);
		}
		if (Memory.playerWinPreviously() == null) {
			return returnValue(AttackTypes.getCounterAttack(Memory
					.getPreviousAttack()));
		}
		if (!Memory.playerWinPreviously() && RANDOM.nextInt(3) != 2) {
			AttackTypes response = null;
			response = AttackTypes.getCounterAttack(Memory.getPreviousAttack());
			return returnValue(response);
		}
		if (RANDOM.nextInt(5) < 3) {
			return returnValue(AttackTypes.PAPER);
		}
		return returnValue(randomStrategy.getEnemyAttack());
	}

	private AttackTypes returnValue(AttackTypes attack) {
		if (attack == null) {
			randomStrategy.getEnemyAttack();
		}
		aiPreviousAttacks.add(attack);
		return attack;
	}

	private boolean hasDoubleRock() {
		return (AttackTypes.STONE.equals(Memory.getPreviousAttack()) && AttackTypes.STONE
				.equals(Memory.getPreviousAttack(1)));
	}

	private AttackTypes getFistAttack() {
		if (RANDOM.nextInt(3) == 1) {
			return AttackTypes.PAPER;
		} else {
			return randomStrategy.getEnemyAttack();
		}
	}

}
