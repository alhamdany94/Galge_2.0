package model;

public class Player {

    String navn;
    int score;

    public Player() {

    }

    public Player(String navn, int score) {
        this.navn = navn;
        this.score = score;

    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
