package br.com.devrmv.model;

import br.com.devrmv.combatCalculations.CombatCalculations;
import br.com.devrmv.interfaces.ICharacter;
import lombok.Getter;

@Getter
public class Barbarian implements ICharacter {
	int hitPoints = 13;
	int str = 6;
	int agi = 3;
	int def = 1;

	@Override
	public int damage() {
        return CombatCalculations.d6() + CombatCalculations.d6() + str;
	}
	@Override
	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}
}
