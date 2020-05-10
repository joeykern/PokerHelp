package model;

import java.util.Objects;

public class Game {

    private int id;
    private String groupId;
    private String gameName;
    private String gameTime;
    private String entryFee;
    private String registrants;

    public Game(String groupId, String gameName, String gameTime,
                String entryFee, String registrants) {
        this.groupId = groupId;
        this.gameName = gameName;
        this.gameTime = gameTime;
        this.entryFee = entryFee;
        this.registrants = registrants;
    }

    public Game(int id, String groupId, String gameName, String gameTime,
                String entryFee, String registrants) {
        this.id = id;
        this.groupId = groupId;
        this.gameName = gameName;
        this.gameTime = gameTime;
        this.entryFee = entryFee;
        this.registrants = registrants;
    }

    public int getId() { return id;}

    public String getGroupId() { return groupId;}

    public String getGameName() {
        return gameName;
    }

    public String getGameTime() {
        return gameTime;
    }

    public String getEntryFee() {
        return entryFee;
    }

    public String getRegistrants() {
        return registrants;
    }

    public void setId(int id) { this.id = id;}

    public void setGroupId(String groupId) { this.groupId = groupId;}

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }

    public void setEntryFee(String entryFee) {
        this.entryFee = entryFee;
    }

    public void setRegistrants(String registrants) {
        this.registrants = registrants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return gameName.equals(game.gameName) &&
                gameTime.equals(game.gameTime) &&
                entryFee.equals(game.entryFee) &&
                Objects.equals(registrants, game.registrants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, gameName, gameTime, entryFee, registrants);
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameName='" + gameName + '\'' +
                ", gameTime='" + gameTime + '\'' +
                ", entryFee='" + entryFee + '\'' +
                ", registrants='" + registrants + '\'' +
                '}';
    }
}
