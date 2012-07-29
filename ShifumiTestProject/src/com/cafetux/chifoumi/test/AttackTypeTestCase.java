package com.cafetux.chifoumi.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.cafetux.chifoumi.model.AttackTypes;

public class AttackTypeTestCase {

	@Test
	public void stone_only_counter_by_paper() {
		assertEquals(AttackTypes.PAPER,
				AttackTypes.getCounterAttack(AttackTypes.STONE));
		assertTrue(AttackTypes.PAPER.winAgainst(AttackTypes.STONE));
		assertNull(AttackTypes.STONE.winAgainst(AttackTypes.STONE));
		assertFalse(AttackTypes.SCISSOR.winAgainst(AttackTypes.STONE));
	}

	@Test
	public void scissor_only_counter_by_stone() {
		assertEquals(AttackTypes.STONE,
				AttackTypes.getCounterAttack(AttackTypes.SCISSOR));
		assertTrue(AttackTypes.STONE.winAgainst(AttackTypes.SCISSOR));
		assertNull(AttackTypes.SCISSOR.winAgainst(AttackTypes.SCISSOR));
		assertFalse(AttackTypes.PAPER.winAgainst(AttackTypes.SCISSOR));
	}

	@Test
	public void paper_only_counter_by_scissor() {
		assertEquals(AttackTypes.SCISSOR,
				AttackTypes.getCounterAttack(AttackTypes.PAPER));
		assertTrue(AttackTypes.SCISSOR.winAgainst(AttackTypes.PAPER));
		assertNull(AttackTypes.PAPER.winAgainst(AttackTypes.PAPER));
		assertFalse(AttackTypes.STONE.winAgainst(AttackTypes.PAPER));
	}
}
