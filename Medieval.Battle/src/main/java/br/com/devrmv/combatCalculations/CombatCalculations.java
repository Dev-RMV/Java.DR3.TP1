package br.com.devrmv.combatCalculations;
import br.com.devrmv.interfaces.ICharacter;

public abstract class CombatCalculations {
	public static int d10() {
		return (int) Math.floor(Math.random() * 10 + 1);
	}

	public static int d8() {
		return (int) Math.floor(Math.random() * 8 + 1);
	}

	public static int d6() {
		return (int) Math.floor(Math.random() * 6 + 1);
	}

	public static int d4() {
		return (int) Math.floor(Math.random() * 4 + 1);
	}

	public static int d2() {
		return (int) Math.floor(Math.random() * 2 + 1);
	}

	public static int attackFactor(int str, int agi) {
		return (int) d10() + str + agi;
	}

	public static int defenseFactor(int def, int agi) {
		return (int) d10() + def + agi;
	}


	public static int initiative(int agi) {
		return (int) d10() + agi;
	}

	public static void playerAttack(ICharacter selectedClass, ICharacter randomMonster) {
		// player ataca
		int attack = CombatCalculations.attackFactor(selectedClass.getAgi(), selectedClass.getStr());
		int defense = CombatCalculations.defenseFactor(randomMonster.getAgi(), randomMonster.getDef());
		int damage = selectedClass.damage();
		System.out.println("Ataque ->" + selectedClass.getClass().getSimpleName() + " :" + attack);
		System.out.println("Defesa ->" + randomMonster.getClass().getSimpleName() + " :" + defense);
		if (attack > defense) {
			System.out.println("Dano: " + damage);
			randomMonster.setHitPoints(randomMonster.getHitPoints() - damage);
			System.out.println(
					"HP atual de " + randomMonster.getClass().getSimpleName() + " " + randomMonster.getHitPoints());
		}
	}

	public static void monsterAttack(ICharacter selectedClass, ICharacter randomMonster) {
		// monstro acata
		int attack = CombatCalculations.attackFactor(randomMonster.getAgi(), randomMonster.getStr());
		int defense = CombatCalculations.defenseFactor(selectedClass.getAgi(), selectedClass.getDef());
		int damage = randomMonster.damage();
		System.out.println("Ataque ->" + randomMonster.getClass().getSimpleName() + " :" + attack);
		System.out.println("Defesa ->" + selectedClass.getClass().getSimpleName() + " :" + defense);
		if (attack > defense) {
			System.out.println("Dano: " + damage);
			selectedClass.setHitPoints(selectedClass.getHitPoints() - damage);
			System.out.println(
					"HP atual de " + selectedClass.getClass().getSimpleName() + " " + selectedClass.getHitPoints());

		}
	}
}