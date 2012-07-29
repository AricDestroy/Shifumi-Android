package com.cafetux.chifoumi.model;

public enum AttackTypes {

	PAPER {
		@Override
		public Boolean winAgainst(AttackTypes attack) {
			if (equals(attack)) {
				return null;
			}
			if (STONE.equals(attack)) {
				return true;
			}
			return false;
		}
	},
	SCISSOR {
		@Override
		public Boolean winAgainst(AttackTypes attack) {
			if (equals(attack)) {
				return null;
			}
			if (PAPER.equals(attack)) {
				return true;
			}
			return false;
		}
	},
	STONE {
		@Override
		public Boolean winAgainst(AttackTypes attack) {
			if (equals(attack)) {
				return null;
			}
			if (SCISSOR.equals(attack)) {
				return true;
			}
			return false;
		}
	};

	abstract public Boolean winAgainst(AttackTypes attack);

	public static AttackTypes getCounterAttack(AttackTypes attack) {
		AttackTypes result = STONE;

		if (attack == null) {
			return result;
		}
		for (int i = 0; i < values().length; i++) {
			Boolean winAgainst = values()[i].winAgainst(attack);
			if (winAgainst != null && winAgainst) {
				result = values()[i];
				break;
			}
		}
		return result;
	}
}
