package stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(1,2,3,4);
        stream.map(i -> i+1).forEach(System.out::println);
    }

    @Test
    public void testGenStream() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream()
                .filter(string -> !string.isEmpty())
                .collect(Collectors.toList());
        filtered.forEach(System.out::println);
    }

}
