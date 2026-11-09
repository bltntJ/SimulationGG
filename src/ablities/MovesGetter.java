package ablities;

import game.Coordinates;

import java.util.ArrayList;
import java.util.List;

public interface MovesGetter {
     default List<Coordinates> getEntityMoves(int speed) {
        List<Coordinates> allPossibleMoves = new ArrayList<>();
        for (int i = -speed; i <= speed; i++) {
            for (int j = -speed; j <= speed; j++) {
                allPossibleMoves.add(new Coordinates(i, j));
            }
        }
        allPossibleMoves.remove(new Coordinates(0, 0));
        return allPossibleMoves;
    }
}
