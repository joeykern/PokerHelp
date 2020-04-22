package model;

import java.util.Objects;

public class Game {
    private String gameId;
    private String gameName;
    private String gameTime;
    private String entryFee;
    private String registrants;

    public Game(String gameId, String gameName, String gameTime,
                String entryFee, String registrants) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.gameTime = gameTime;
        this.entryFee = entryFee;
        this.registrants = registrants;
    }

    public String getGameId() {
        return gameId;
    }

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

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

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
        return gameId.equals(game.gameId) &&
                gameName.equals(game.gameName) &&
                gameTime.equals(game.gameTime) &&
                entryFee.equals(game.entryFee) &&
                Objects.equals(registrants, game.registrants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, gameName, gameTime, entryFee, registrants);
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId='" + gameId + '\'' +
                ", gameName='" + gameName + '\'' +
                ", gameTime='" + gameTime + '\'' +
                ", entryFee='" + entryFee + '\'' +
                ", registrants='" + registrants + '\'' +
                '}';
    }
}
