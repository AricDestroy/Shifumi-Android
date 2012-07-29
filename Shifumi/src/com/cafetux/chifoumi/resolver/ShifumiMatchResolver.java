package com.cafetux.chifoumi.resolver;

import android.content.Context;
import android.util.Log;

import com.cafetux.chifoumi.R;
import com.cafetux.chifoumi.model.AttackTypes;
import com.cafetux.chifoumi.resolver.ai.AiStrategyFactory;

public class ShifumiMatchResolver {

	public interface EnemyStrategy {
		AttackTypes getEnemyAttack();
	}

	private final AttackTypes playerAttack;
	private AttackTypes ennemyAttack;
	private final EnemyStrategy strategy = AiStrategyFactory.getStrategy();
	private final Context context;

	public ShifumiMatchResolver(Context context, AttackTypes playerAttack) {
		this.playerAttack = playerAttack;
		this.context = context;
	}

	public Boolean isPlayerWin() {
		ennemyAttack = strategy.getEnemyAttack();
		Log.d("debug", "enemy:" + ennemyAttack);
		Boolean playerWin = playerAttack.winAgainst(ennemyAttack);
		Log.d("debug", "win:" + playerWin);

		return playerWin;
	}

	@Override
	public String toString() {
		Log.d("debug", "ToString enemy:" + ennemyAttack);

		StringBuilder res = new StringBuilder();
		if (playerAttack.winAgainst(ennemyAttack) == null) {
			res.append("Match Null !");
		} else {
			res.append(playerAttack.winAgainst(ennemyAttack) ? "Gagné"
					: "Perdu");
			res.append(" contre ").append(getAttackLabel(ennemyAttack));
		}
		return res.toString();
	}

	public String getAttackLabel(AttackTypes attack) {
		String label;
		switch (attack) {
		case STONE:
			label = context.getString(R.string.rock_label);
			break;
		case SCISSOR:
			label = context.getString(R.string.scissor_label);
			break;
		case PAPER:
			label = context.getString(R.string.paper_label);
			break;
		default:
			label = "invalid attack";

		}
		return label;
	}
}
