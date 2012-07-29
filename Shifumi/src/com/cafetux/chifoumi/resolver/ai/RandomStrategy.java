package com.cafetux.chifoumi.resolver.ai;

import java.util.Random;

import com.cafetux.chifoumi.model.AttackTypes;
import com.cafetux.chifoumi.resolver.ShifumiMatchResolver.EnemyStrategy;

public class RandomStrategy implements EnemyStrategy {

	private static final Random RANDOM = new Random();

	RandomStrategy() {

	}

	@Override
	public AttackTypes getEnemyAttack() {

		return AttackTypes.values()[RANDOM.nextInt(AttackTypes.values().length)];
	}

}
