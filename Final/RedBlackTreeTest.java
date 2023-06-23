package Final;

import java.util.Scanner;

public class RedBlackTreeTest {
    public static void main(String[] args) {
        RedBlackTreeBin tree = new RedBlackTreeBin();
        System.out.println("Enter a number:");
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                int value = Integer.parseInt(sc.nextLine());
                tree.add(value);
                System.out.println("Enter a number:");
            }
        }
    }
}


