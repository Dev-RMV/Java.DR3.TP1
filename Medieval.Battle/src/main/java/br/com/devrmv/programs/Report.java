package br.com.devrmv.programs;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import br.com.devrmv.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Report {
    Logger LOGGER = LoggerFactory.getLogger(Report.class);
    Scanner sc = new Scanner(System.in);
    String nickName = "";

    public void run(){
        System.out.print("Digite o nickname do jogador: ");

        try {
            nickName = sc.nextLine();
        } catch (Exception e) {
            LOGGER.error(e.toString());
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
            LOGGER.error(e.toString());
        }

        final int[] maxHeroCount = {0};
        final int[] maxMonsterCount = {0};
        final int[] totalPoints = {0};
        final String[] mostPlayedHero = {""};
        final String[] mostFacedMonster = {""};

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

