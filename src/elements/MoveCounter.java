package elements;

public class MoveCounter {
    private int count = 0;

    public void printCount() {
        count++;
        System.out.printf("Moves: %d\n",count);
    }
}
