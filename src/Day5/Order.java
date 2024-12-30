package Day5;

public class Order {
    private final int first;
    private final int last;

    public Order(String pair) {
        String[] split = pair.split("\\|");
        this.first = Integer.parseInt(split[0]);
        this.last = Integer.parseInt(split[1]);
    }

    public int getFirst() {
        return first;
    }

    public int getLast() {
        return last;
    }
}
