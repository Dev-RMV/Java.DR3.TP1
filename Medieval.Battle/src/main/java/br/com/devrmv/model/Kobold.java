package br.com.devrmv.model;

import br.com.devrmv.combatCalculations.CombatCalculations;
import br.com.devrmv.interfaces.ICharacter;
import lombok.Getter;

@Getter
public class Kobold implements ICharacter {

	int hitPoints = 20;
	int str = 2;
	int agi = 4;
	int def = 2;
	@Override
	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}

	@Override
	public int damage() {
        // Auto-generated
																										// method stub
		return CombatCalculations.d2() + CombatCalculations.d2() + CombatCalculations.d2() + str;
	}
}
