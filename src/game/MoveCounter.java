package game;

public class MoveCounter {
    private int count = 0;

    public void increaseCount() {
        count++;
        System.out.println("--- Moves: " + count + " ---\n");
    }
}
