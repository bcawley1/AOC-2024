package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //Parse Input
        Scanner scanner = new Scanner(new File("src/Day2/input.txt"));
        List<List<Integer>> data = new ArrayList<>();
        while (scanner.hasNextLine()) {
            data.add(Arrays.stream(scanner.nextLine().split(" "))
                    .map(Integer::parseInt)
                    .toList());
        }

        long part1Safe = data.stream()
                .filter(Main::allIncreasingOrDecreasing)
                .filter(Main::checkDifference)
                .count();

        System.out.printf("Part 1 safe reports: %d\n", part1Safe);

        //Part 2

        long newSafe = data.stream()
                .filter(l -> !(allIncreasingOrDecreasing(l) && checkDifference(l))) //gets all unsafe cases
                .filter(l -> {
                    boolean canRemove = false;
                    for (int i = 0; i < l.size(); i++) {
                        List<Integer> removedList = new ArrayList<>(l);
                        removedList.remove(i);
                        if (allIncreasingOrDecreasing(removedList) && checkDifference(removedList)){
                            return true;
                        }
                    }
                    return false;
                })
                .count();

        System.out.println(newSafe + part1Safe);

    }

    private static boolean allIncreasingOrDecreasing(List<Integer> list) {
        boolean increasing = list.get(0) < list.get(1);
        for (int i = 1; i < list.size() - 1; i++) {
            if ((increasing && list.get(i) > list.get(i + 1)) || (!increasing && list.get(i) < list.get(i + 1))) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkDifference(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            int diff = Math.abs(list.get(i) - list.get(i + 1));
            if (!(diff <= 3 && diff >= 1)) {
                return false;
            }
        }
        return true;
    }
}
