import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import br.com.devrmv.combatCalculations.CombatCalculations;
import br.com.devrmv.interfaces.ICharacter;
import br.com.devrmv.model.*;


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nickName = "";
        int attack, defense, damage = 0;
        ICharacter selectedClass = null;
        ICharacter randomMonster = null;
        int option = 0;

        System.out.println("Bem vindo ao AT de Fundamentos de Java!");
        System.out.print("\nO que deseja?\n1-Jogar\n2-Ver relatório\nOpcão: ");
        try {
            option = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Ocorreu um erro.");// TODO: handle exception
        }
        sc.nextLine();

        switch (option) {
            case 1: {
                System.out.print("\nEste jogo se chama Medieval Battle, e para começar, insira seu Nickname: ");
                try {
                    nickName = sc.nextLine();
                } catch (Exception e) {
                    System.out.println("Ocorreu um erro ao inserir o nickname");
                }
                System.out.println("\nMuito bem " + nickName + "! Agora, escolha sua classe...");
                System.out.println("\n1-Fighter\n2-Paladin\n3-Barbarian");
                System.out.print("Sua escolha: ");

                switch (sc.nextInt()) {
                    case 1: {
                        selectedClass = new Fighter();
                        break;
                    }
                    case 2: {
                        selectedClass = new Paladin();
                        break;
                    }
                    case 3: {
                        selectedClass = new Barbarian();
                        break;
                    }
                }
//Monstro aleatório
                switch ((int) Math.floor(Math.random() * 3 + 1)) {
                    case 1: {
                        randomMonster = new Undead();
                        break;
                    }
                    case 2: {
                        randomMonster = new Orc();
                        break;
                    }
                    case 3: {
                        randomMonster = new Kobold();
                        break;
                    }
                }

                System.out.println("Nick:" + nickName + "\nClasse: " + selectedClass.getClass());
                System.out.println("VERSUS: " + randomMonster.getClass());
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
                } catch (Exception e) {
                    System.out.println("Erro ao gravar log de combate!");
                }

                sc.close();
                break;
            }
            case 2: {
                System.out.print("Digite o nickname do jogador: ");

                try {
                    nickName = sc.nextLine();
                } catch (Exception e) {
                    System.out.println("Ocorreu um erro ao inserir o nickname");
                }

                File file = new File("temp/" + nickName + ".csv");
                ArrayList<LogObject> records = new ArrayList<>();

                try {
                    Scanner fileScanner = new Scanner(file);
                    while (fileScanner.hasNextLine()) {
                        String line = fileScanner.nextLine();
                        String[] parts = line.split(";");

                        LogObject record = new LogObject();
                        record.setDate(parts[0]);
                        record.setSelectecClass(parts[1]);
                        record.setResult(parts[2]);
                        record.setRandomMonster(parts[3]);
                        record.setRounds(Integer.parseInt(parts[4]));

                        records.add(record);
                    }
                    fileScanner.close();
                } catch (Exception e) {
                    System.out.println("Arquivo não encontrado.");
                }

                final int[] maxHeroCount = { 0 };
                final int[] maxMonsterCount = { 0 };
                final int[] totalPoints = { 0 };
                final String[] mostPlayedHero = { "" };
                final String[] mostFacedMonster = { "" };

                records.forEach(record -> {
                    int heroCount = 0;
                    int monsterCount = 0;

                    for (LogObject r : records) {
                        if (r.getSelectecClass().equals(record.getSelectecClass())) {
                            heroCount++;
                        }
                        if (r.getRandomMonster().equals(record.getRandomMonster())) {
                            monsterCount++;
                        }
                    }

                    if (heroCount > maxHeroCount[0]) {
                        mostPlayedHero[0] = record.getSelectecClass();
                        maxHeroCount[0] = heroCount;
                    }

                    if (monsterCount > maxMonsterCount[0]) {
                        mostFacedMonster[0] = record.getRandomMonster();
                        maxMonsterCount[0] = monsterCount;
                    }

                    if (record.getResult().equals("GANHOU")) totalPoints[0] += 100 - record.getRounds();
                });

                System.out.println("Herói mais jogado: " + mostPlayedHero[0]);
                System.out.println("Monstro mais enfrentado: " + mostFacedMonster[0]);
                System.out.println("Pontuação total: " + totalPoints[0]);
            }
        }
    }
}