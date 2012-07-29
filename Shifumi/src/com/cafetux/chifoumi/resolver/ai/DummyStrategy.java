package com.cafetux.chifoumi.resolver.ai;

import com.cafetux.chifoumi.model.AttackTypes;
import com.cafetux.chifoumi.resolver.ShifumiMatchResolver.EnemyStrategy;

public class DummyStrategy implements EnemyStrategy {

	DummyStrategy() {

	}

	@Override
	public AttackTypes getEnemyAttack() {
		return AttackTypes.STONE;
	}

}
