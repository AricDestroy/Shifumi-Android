package com.cafetux.chifoumi.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.cafetux.chifoumi.model.AttackTypes;
import com.cafetux.chifoumi.resolver.Memory;

public class MemoryTestCase {

	@Before
	public void initTest() {
		Memory.reset();
	}

	@Test
	public void test_memory_previous_player_action() {
		Memory.addResult(AttackTypes.STONE, true);
		assertEquals(AttackTypes.STONE, Memory.getPreviousAttack());
	}

	@Test
	public void test_memory_previous_player_action_after_multiple_actions() {
		Memory.addResult(AttackTypes.STONE, true);
		Memory.addResult(AttackTypes.STONE, true);
		Memory.addResult(AttackTypes.SCISSOR, null);
		Memory.addResult(AttackTypes.PAPER, true);
		Memory.addResult(AttackTypes.STONE, false);
		Memory.addResult(AttackTypes.PAPER, true);
		assertEquals(AttackTypes.PAPER, Memory.getPreviousAttack());
		assertEquals(AttackTypes.PAPER, Memory.getPreviousAttack(0));
		assertEquals(AttackTypes.STONE, Memory.getPreviousAttack(1));
		assertEquals(AttackTypes.PAPER, Memory.getPreviousAttack(2));
		assertEquals(AttackTypes.SCISSOR, Memory.getPreviousAttack(3));
	}

	@Test
	public void test_win_stats() {
		Memory.addResult(AttackTypes.STONE, true);
		Memory.addResult(AttackTypes.STONE, true);
		Memory.addResult(AttackTypes.SCISSOR, null);
		Memory.addResult(AttackTypes.PAPER, null);
		Memory.addResult(AttackTypes.STONE, false);
		Memory.addResult(AttackTypes.PAPER, true);
		Memory.addResult(AttackTypes.STONE, true);
		Memory.addResult(AttackTypes.STONE, true);
		Memory.addResult(AttackTypes.SCISSOR, null);
		Memory.addResult(AttackTypes.PAPER, true);
		Memory.addResult(AttackTypes.STONE, false);
		Memory.addResult(AttackTypes.PAPER, true);
		Memory.addResult(AttackTypes.PAPER, true);
		assertEquals(8, Memory.getNbWin());
		assertEquals(2, Memory.getNbConsecutiveWin());
		assertEquals(3, Memory.getNbDraw());
		assertEquals(13, Memory.getNbGames());

	}

	@Test
	public void test_consecutive_win_stats_not_break_by_draw() {
		Memory.addResult(AttackTypes.STONE, true);
		Memory.addResult(AttackTypes.STONE, true);
		Memory.addResult(AttackTypes.SCISSOR, null);
		Memory.addResult(AttackTypes.PAPER, true);
		Memory.addResult(AttackTypes.STONE, false);
		Memory.addResult(AttackTypes.PAPER, true);
		Memory.addResult(AttackTypes.STONE, true);
		Memory.addResult(AttackTypes.STONE, true);
		Memory.addResult(AttackTypes.SCISSOR, null);
		Memory.addResult(AttackTypes.PAPER, true);
		Memory.addResult(AttackTypes.STONE, null);
		Memory.addResult(AttackTypes.PAPER, true);
		Memory.addResult(AttackTypes.PAPER, true);
		assertEquals(6, Memory.getNbConsecutiveWin());
	}

	@Test
	public void get_most_prefered_player_attack_is_stone() {
		Memory.addResult(AttackTypes.STONE, true);
		Memory.addResult(AttackTypes.STONE, true);
		Memory.addResult(AttackTypes.SCISSOR, null);
		Memory.addResult(AttackTypes.PAPER, true);
		Memory.addResult(AttackTypes.STONE, false);
		Memory.addResult(AttackTypes.PAPER, true);
		Memory.addResult(AttackTypes.STONE, true);
		Memory.addResult(AttackTypes.STONE, true);
		Memory.addResult(AttackTypes.SCISSOR, null);
		Memory.addResult(AttackTypes.PAPER, true);
		Memory.addResult(AttackTypes.STONE, null);
		Memory.addResult(AttackTypes.PAPER, true);
		Memory.addResult(AttackTypes.PAPER, true);
		assertEquals(AttackTypes.STONE, Memory.getMostAttackChoiced());

	}

	@Test
	public void get_most_prefered_player_attack_is_scissor() {
		Memory.addResult(AttackTypes.STONE, true);
		Memory.addResult(AttackTypes.STONE, true);
		Memory.addResult(AttackTypes.SCISSOR, null);
		Memory.addResult(AttackTypes.PAPER, true);
		Memory.addResult(AttackTypes.STONE, false);
		Memory.addResult(AttackTypes.PAPER, true);
		Memory.addResult(AttackTypes.STONE, true);
		Memory.addResult(AttackTypes.SCISSOR, true);
		Memory.addResult(AttackTypes.SCISSOR, null);
		Memory.addResult(AttackTypes.PAPER, true);
		Memory.addResult(AttackTypes.SCISSOR, null);
		Memory.addResult(AttackTypes.SCISSOR, true);
		Memory.addResult(AttackTypes.SCISSOR, true);
		assertEquals(AttackTypes.SCISSOR, Memory.getMostAttackChoiced());

	}

	@Test
	public void get_most_prefered_player_attack_is_paper() {
		Memory.addResult(AttackTypes.STONE, true);
		Memory.addResult(AttackTypes.PAPER, true);
		Memory.addResult(AttackTypes.SCISSOR, null);
		Memory.addResult(AttackTypes.PAPER, true);
		Memory.addResult(AttackTypes.STONE, false);
		Memory.addResult(AttackTypes.PAPER, true);
		Memory.addResult(AttackTypes.PAPER, true);
		Memory.addResult(AttackTypes.SCISSOR, true);
		Memory.addResult(AttackTypes.PAPER, null);
		Memory.addResult(AttackTypes.PAPER, true);
		Memory.addResult(AttackTypes.PAPER, null);
		Memory.addResult(AttackTypes.PAPER, true);
		Memory.addResult(AttackTypes.SCISSOR, true);
		assertEquals(AttackTypes.PAPER, Memory.getMostAttackChoiced());

	}
}
