package ru.pyply.games.mobilehorrorquest;

public class Situation {
    Situation[] direction;
    String subject,text;
    public boolean isFirstCheck;
    int dh, dk;
    public Situation (String subject, String text, int variants, int dh,int dk) {
        this.subject=subject;
        this.text=text;
        this.isFirstCheck = true;
        this.dh = dh;
        this.dk = dk;

        direction=new Situation[variants];
    }
}