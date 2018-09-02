package stream;

import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(1,2,3,4);
        stream.map(i -> i+1).forEach(System.out::println);
    }
}
