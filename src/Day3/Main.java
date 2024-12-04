package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //Parse Input
        Scanner scanner = new Scanner(new File("src/Day3/input.txt"));
        List<String> input = new ArrayList<>();
        while (scanner.hasNextLine()) {
            input.add(scanner.nextLine());
        }

        //Part 1
        System.out.printf("Part 1 sum: %d\n", sumOfMul(input));

        //Part 2
        StringBuilder sb = new StringBuilder();
        input.forEach(sb::append);
        String combinedInput = sb.toString();

        String[] allowedData = combinedInput.split("don't\\(\\)");
        for (int i = 1; i < allowedData.length; i++) {
            int indexOf = allowedData[i].indexOf("do()");
            allowedData[i] = indexOf != -1 ? allowedData[i].substring(indexOf) : "";
        }

        System.out.printf("Part 2 sum: %d\n", sumOfMul(Arrays.asList(allowedData)));
    }

    private static int sumOfMul(List<String> input){
        int sum = 0;
        for (String line : input) {
            sum += Arrays.stream(line.replaceAll("(?!(mul\\(([0-9]{1,3}),([0-9]{1,3})\\))).", "$1").split("mmul\\("))
                    .filter(s -> !s.isEmpty())
                    .mapToInt(s -> Arrays.stream(s.substring(0, s.length() - 1).split(","))
                            .mapToInt(Integer::parseInt)
                            .reduce(1, (x, y) -> x * y))
                    .sum();
        }
        return sum;
    }
}