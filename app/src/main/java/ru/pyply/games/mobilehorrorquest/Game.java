package ru.pyply.games.mobilehorrorquest;

import java.util.Scanner;

public class Game {

    public static Character player;
    public static Story story;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите ваше имя:");
        player = new Character(in.next());
        story = new Story();
        while (true) {
            if (story.current_situation.isFirstCheck) {
                story.current_situation.isFirstCheck = false;
                player.addHealth(story.current_situation.dh);
                player.addKnowledge(story.current_situation.dk);
            }

            System.out.println("=============" + "ХП: " + player.getHealth() + " Знания: " + player.getKnowledge() + "%" +
                    "=============");
            System.out.println("============="
                    + story.current_situation.subject + "==============");
            System.out.println(story.current_situation.text);
            if (story.isEnd()) {
                break;
            }
            story.go(in.nextInt());
        }

        if (player.getHealth() <= 0)
            System.out.println("Вы умерли!");
         else if (player.getKnowledge() <= 100) {
            System.out.println(player.name + ", Вы живы, но расследование не завершенно, а убийства не закончились.");
        } else {
            System.out.println(player.name + ", Вы нашли все улики и раскрыли дело! Маньяком был уборщик этого дома." +
                    "\n Его оскорбляли подростки, из-за чего он сильно обиделся и начал их убивать." +
                    " Вам дали премию за свои заслуги!");
        }
        System.out.println("====================the end!===================");

    }

}