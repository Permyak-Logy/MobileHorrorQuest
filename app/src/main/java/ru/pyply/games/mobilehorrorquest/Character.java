package ru.pyply.games.mobilehorrorquest;


public class Character {
    private int health;
    private int knowledge;
    public String name;

    public Character(String name) {
        health = 3;
        knowledge = 0;
        this.name = name;
    }
    public void addHealth(int value) {
        this.health += value;
    }
    public void addKnowledge(int value) {
        this.knowledge += value;
    }

    public int getHealth() {
        return this.health;
    }
    public int getKnowledge() {
        return this.knowledge;
    }
}
