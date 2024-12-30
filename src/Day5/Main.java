package Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/Day5/input.txt"));
        List<Order> orders = new ArrayList<>();
        List<PrintQueue> queues = new ArrayList<>();

        //Parse Input
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            if (line.contains("|")){
                orders.add(new Order(line));
            } else {
                queues.add(new PrintQueue(line));
            }
        }

        int sum = queues.stream()
                .filter(queue -> queue.isCorrect(orders))
                .mapToInt(PrintQueue::getMiddleNum)
                .sum();

        System.out.println(sum);

        int correctedSum = queues.stream()
                .filter(queue -> !queue.isCorrect(orders))
                .map(queue -> queue.correct(orders))
                .mapToInt(PrintQueue::getMiddleNum)
                .sum();

        System.out.println(correctedSum);
    }
}
