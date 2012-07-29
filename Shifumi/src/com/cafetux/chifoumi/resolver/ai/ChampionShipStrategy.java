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

			Log.d("Strategy", "isFIRST");
			return returnValue(getFistAttack());
		}
		Log.d("Strategy", "hasDoubleRock:" + Memory.getPreviousAttack()
				+ Memory.getPreviousAttack(1));

		if (hasDoubleRock()) {
			Log.d("Strategy", "hasDoubleRock");
			return returnValue(AttackTypes.STONE);
		}
		if (Memory.playerWinPreviously() == null) {

			Log.d("Strategy",
					"previously DRAW: joue le contre du contre du draw");
			return returnValue(AttackTypes.getCounterAttack(AttackTypes
					.getCounterAttack(Memory.getPreviousAttack())));
		}
		if (!Memory.playerWinPreviously() && RANDOM.nextInt(3) != 2) {
			Log.d("Strategy", "player Lose Previously (et random)");
			AttackTypes response = null;
			response = AttackTypes.getCounterAttack(Memory.getPreviousAttack());
			return returnValue(response);
		}
		if (RANDOM.nextInt(4) == 3) {
			Log.d("Strategy", "random Papier");

			return returnValue(AttackTypes.PAPER);
		}
		Log.d("Strategy", "default attack");

		return returnValue(getDefaultAttack());
	}

	private AttackTypes getDefaultAttack() {
		return AttackTypes.getCounterAttack(Memory.getMostAttackChoiced());
	}

	private AttackTypes returnValue(AttackTypes attack) {
		if (attack == null) {
			getDefaultAttack();
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
