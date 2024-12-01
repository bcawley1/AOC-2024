package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //Parse Input
        Scanner scanner = new Scanner(new File("src/Day1/input.txt"));
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split("   ");
            list1.add(Integer.parseInt(line[0]));
            list2.add(Integer.parseInt(line[1]));
        }

        Collections.sort(list1);
        Collections.sort(list2);

        //Part 1
        int totalDistance = 0;
        for (int i = 0; i < list1.size(); i++) {
            totalDistance += Math.abs(list1.get(i) - list2.get(i));
        }

        System.out.printf("The total distance is: %d\n", totalDistance);

        //Part2
        int SimilaritySum = list1.stream()
                .mapToInt(num -> num * occurrences(list2, num))
                .sum();

        System.out.printf("The sum of the similarity scores is: %d\n", SimilaritySum);
    }

    public static int occurrences(List<Integer> list, int num) {
        int occurrences = 0;
        for (Integer i : list) {
            if (i.intValue() == num){
                occurrences++;
            }
        }
        return occurrences;
    }
}