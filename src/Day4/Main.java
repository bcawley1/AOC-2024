package Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //Parse Input
        Scanner scanner = new Scanner(new File("src/Day4/input.txt"));
        List<String> lines = new ArrayList<>();
        int numOccurrences = 0;

        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        int width = lines.get(0).length();

        //Part 1
        //Horizontal
        numOccurrences += lines.stream()
                .mapToInt(line -> numOccurrences(line, "XMAS") + numOccurrences(line, "SAMX"))
                .sum();


        //Diagonal and vertical
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).toCharArray().length; j++) {
                if (lines.get(i).charAt(j) == 'X') {
                    //Up
                    if (i > 2) {
                        if (lines.get(i - 1).charAt(j) == 'M' && lines.get(i - 2).charAt(j) == 'A' && lines.get(i - 3).charAt(j) == 'S') {
                            numOccurrences++;
                        }
                    }
                    //Down
                    if (i < lines.size() - 3) {
                        if (lines.get(i + 1).charAt(j) == 'M' && lines.get(i + 2).charAt(j) == 'A' && lines.get(i + 3).charAt(j) == 'S') {
                            numOccurrences++;
                        }
                    }
                    //Up right
                    if (i > 2 && j < width - 3) {
                        if (lines.get(i - 1).charAt(j + 1) == 'M' && lines.get(i - 2).charAt(j + 2) == 'A' && lines.get(i - 3).charAt(j + 3) == 'S') {
                            numOccurrences++;
                        }
                    }
                    //Down right
                    if (i < lines.size() - 3 && j < width - 3) {
                        if (lines.get(i + 1).charAt(j + 1) == 'M' && lines.get(i + 2).charAt(j + 2) == 'A' && lines.get(i + 3).charAt(j + 3) == 'S') {
                            numOccurrences++;
                        }
                    }
                    //Down left
                    if (i < lines.size() - 3 && j > 2) {
                        if (lines.get(i + 1).charAt(j - 1) == 'M' && lines.get(i + 2).charAt(j - 2) == 'A' && lines.get(i + 3).charAt(j - 3) == 'S') {
                            numOccurrences++;
                        }
                    }
                    //Up left
                    if (i > 2 && j > 2) {
                        if (lines.get(i - 1).charAt(j - 1) == 'M' && lines.get(i - 2).charAt(j - 2) == 'A' && lines.get(i - 3).charAt(j - 3) == 'S') {
                            numOccurrences++;
                        }
                    }
                }
            }
        }

        System.out.println(numOccurrences);

        //Part 2
        int numOccurrences2 = 0;

        for (int i = 1; i < lines.size() - 1; i++) {
            for (int j = 1; j < lines.get(i).toCharArray().length - 1; j++) {
                if(lines.get(i).charAt(j) == 'A' &&
                        ((lines.get(i-1).charAt(j-1) == 'M' && lines.get(i+1).charAt(j+1) == 'S') || (lines.get(i-1).charAt(j-1) == 'S' && lines.get(i+1).charAt(j+1) == 'M')) &&
                        ((lines.get(i-1).charAt(j+1) == 'M' && lines.get(i+1).charAt(j-1) == 'S') || (lines.get(i-1).charAt(j+1) == 'S' && lines.get(i+1).charAt(j-1) == 'M'))){
                    numOccurrences2++;
                }
            }
        }
        System.out.println(numOccurrences2);
    }

    private static int numOccurrences(String line, String match) {
        int num = 0;
        int searchIndex = 0;
        while (searchIndex != -1) {
            int index = line.indexOf(match, searchIndex);
            searchIndex = index == -1 ? -1 : index + 1;
            num += searchIndex == -1 ? 0 : 1;
        }
        return num;
    }
}
