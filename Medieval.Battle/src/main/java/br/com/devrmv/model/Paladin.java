package br.com.devrmv.model;

import br.com.devrmv.combatCalculations.CombatCalculations;
import br.com.devrmv.interfaces.ICharacter;
import lombok.Getter;

@Getter

public class Paladin implements ICharacter {
	int hitPoints = 15;
	int str = 2;
	int agi = 1;
	int def = 5;

	@Override
	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}

	@Override
	public int damage() {
        return CombatCalculations.d4() + CombatCalculations.d4() + str;
	}
}
