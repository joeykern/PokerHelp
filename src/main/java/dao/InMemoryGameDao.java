package dao;

import model.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryGameDao implements GameDao {
    private List<Game> gameList;

    public InMemoryGameDao() {
        gameList = new ArrayList<>();
    }

    @Override
    public void add(Game game) {
        gameList.add(game);
    }

    @Override
    public List<Game> findAll() {
        return Collections.unmodifiableList(gameList);
    }
}
