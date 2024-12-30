package Day5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PrintQueue {
    private List<Integer> numbers;

    public PrintQueue(String line) {
        this.numbers = Arrays.stream(line.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public boolean isCorrect(List<Order> orders) {
        return orders.stream()
                .filter(order -> numbers.contains(order.getFirst()) && numbers.contains(order.getLast()))
                .allMatch(order -> numbers.indexOf(order.getFirst()) < numbers.indexOf(order.getLast()));
    }

    public PrintQueue correct(List<Order> orders){
        List<Order> failedOrders = orders.stream()
                .filter(order -> numbers.contains(order.getFirst()) && numbers.contains(order.getLast()))
                .filter(order -> !(numbers.indexOf(order.getFirst()) < numbers.indexOf(order.getLast())))
                .toList();

        for (Order failedOrder : failedOrders) {
             int firstIndex = numbers.indexOf(failedOrder.getFirst());
             int lastIndex = numbers.indexOf(failedOrder.getLast());
             numbers.set(firstIndex, failedOrder.getLast());
             numbers.set(lastIndex, failedOrder.getFirst());
        }

        if (isCorrect(orders)){
            return this;
        } else {
            return correct(orders);
        }
    }

    public int getMiddleNum() {
        return numbers.get((numbers.size() - 1) / 2);
    }
}
