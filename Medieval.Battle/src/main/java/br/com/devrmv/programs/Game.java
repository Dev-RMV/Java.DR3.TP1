package br.com.devrmv.programs;

import br.com.devrmv.combatCalculations.CombatCalculations;
import br.com.devrmv.interfaces.ICharacter;
import br.com.devrmv.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Game {
    Logger LOGGER = LoggerFactory.getLogger(Game.class);
    public void run() {

        Scanner sc = new Scanner(System.in);
        String nickName = "";
        ICharacter selectedClass;
        ICharacter randomMonster = null;
        int option = 0;
        System.out.print("\nEste jogo se chama Medieval Battle, e para começar, insira seu Nickname: ");
        try {
            nickName = sc.nextLine();
        } catch (Exception e) {
            LOGGER.error(e.toString());
        }
        System.out.println("\nMuito bem " + nickName + "! Agora, escolha sua classe...");
        System.out.println("\n1-Fighter\n2-Paladin\n3-Barbarian");
        System.out.print("Sua escolha: ");

        selectedClass = characterSelect(sc.nextInt());

        //Monstro aleatório
        randomMonster=monsterSelect((int) Math.floor(Math.random() * 3 + 1));

        System.out.println("Nick:" + nickName + "\nClasse: " + selectedClass.getClass().getSimpleName());
        System.out.println("VERSUS: " + randomMonster.getClass().getSimpleName());
        int round = 1;
        while (selectedClass.getHitPoints() > 0 || randomMonster.getHitPoints() > 0) {
            System.out.println("\n******Round " + round + "******");

            int playerInitiative = CombatCalculations.initiative(selectedClass.getAgi());
            int monsterInitiative = CombatCalculations.initiative(randomMonster.getAgi());
            while (playerInitiative == monsterInitiative) {
                playerInitiative = CombatCalculations.initiative(selectedClass.getAgi());
                monsterInitiative = CombatCalculations.initiative(randomMonster.getAgi());
            }

            if (playerInitiative > monsterInitiative) {
                // player ataca
                CombatCalculations.playerAttack(selectedClass, randomMonster);
                if (randomMonster.getHitPoints() <= 0)
                    break;

                // monstro acata
                CombatCalculations.monsterAttack(selectedClass, randomMonster);
                if (selectedClass.getHitPoints() <= 0)
                    break;

            } else {
                // monstro ataca
                CombatCalculations.monsterAttack(selectedClass, randomMonster);
                if (selectedClass.getHitPoints() <= 0)
                    break;

                // player ataca
                CombatCalculations.playerAttack(selectedClass, randomMonster);
                if (randomMonster.getHitPoints() <= 0)
                    break;
            }
            round++;
        }
        try {
            new File("temp").mkdir();
            // true adiciona ao final
            PrintWriter writer = new PrintWriter(new FileOutputStream(new File("temp/" + nickName + ".csv"), true));
            String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
            String result = selectedClass.getHitPoints() > 0 ? "GANHOU" : "PERDEU";
            writer.println(date + ";" + selectedClass.getClass().getSimpleName() + ";" + result + ";"
                    + randomMonster.getClass().getSimpleName() + ";" + round);
            writer.close();
            LOGGER.info("Log de combate salvo com sucesso.");
        } catch (Exception e) {
            LOGGER.error(e.toString());
        }
        sc.close();
    }

    public ICharacter characterSelect(int input) {
        switch (input) {
            case 1:
                return new Fighter();
            case 2:
                return new Paladin();
            case 3:
                return new Barbarian();
            default:
                LOGGER.error("Nenhuma classe valida foi escolhida e isto causara uma exception - DEFAULT");
                throw new RuntimeException("Nenhuma classe valida foi escolhida");
        }
    }
    public ICharacter monsterSelect(int randomNumber) {
        switch (randomNumber) {
            case 1:
                return new Undead();
            case 2:
                return new Orc();
            case 3:
                return new Kobold();
            default:
                LOGGER.debug("Nenhuma monstro valido foi escolhido - DEFAULT");
                return null;
        }
    }
    public void saveBattle(){

    }
}