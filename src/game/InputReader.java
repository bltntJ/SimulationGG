package game;

import java.util.Scanner;

public class InputReader implements Runnable {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        while (true) {
            String number = scanner.next().trim();
            if (number.equals("0")) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Enter a correct option!");
            }
        }
    }
}
