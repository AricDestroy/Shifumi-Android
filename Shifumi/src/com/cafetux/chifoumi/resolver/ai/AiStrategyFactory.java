package com.cafetux.chifoumi.resolver.ai;

import com.cafetux.chifoumi.resolver.ShifumiMatchResolver.EnemyStrategy;

public class AiStrategyFactory {

	public enum DIFFICULTY {
		DUMMY {
			@Override
			EnemyStrategy getStrategy() {

				return new DummyStrategy();
			}
		},
		NORMAL {
			@Override
			EnemyStrategy getStrategy() {
				return new RandomStrategy();
			}
		},
		HARD {
			@Override
			EnemyStrategy getStrategy() {
				return new ChampionShipStrategy();
			}
		};
		abstract EnemyStrategy getStrategy();
	}

	private AiStrategyFactory() {

	}

	private static DIFFICULTY AI_LEVEL = DIFFICULTY.NORMAL;

	public static EnemyStrategy getStrategy() {
		return AI_LEVEL.getStrategy();
	}

	public static void setDifficulty(DIFFICULTY aiLevel) {
		AiStrategyFactory.AI_LEVEL = aiLevel;
	}
}
