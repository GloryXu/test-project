package consistency.hash;

import org.junit.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ConsistentHashTest {

    @Test
    public void testConsistentHash() {
        Set<String> nodes = new HashSet<>();
        nodes.add("A");
        nodes.add("B");
        nodes.add("C");
        int aCount = 0,bCount = 0,cCount = 0;

        ConsistentHash<String> consistentHash = new ConsistentHash<>(new HashFunction(), 2, nodes);

        for (int i = 0; i<1000000;i++) {
            String currentNode = consistentHash.get(Integer.toString((char) new Random().nextInt(128)));
            if ("A".equals(currentNode)) {
                aCount++;
            } else if ("B".equals(currentNode)) {
                bCount++;
            } else {
                cCount++;
            }
        }
        System.out.println("A 节点数量：" + aCount);
        System.out.println("B 节点数量：" + bCount);
        System.out.println("C 节点数量：" + cCount);
    }

    public static void main(String[] args) {
        Set<String> nodes = new HashSet<>();
        nodes.add("A");
        nodes.add("B");
        nodes.add("C");

        ConsistentHash<String> consistentHash = new ConsistentHash<>(new HashFunction(), 2, nodes);
        consistentHash.add("D");

        System.out.println("hash circle size: " + consistentHash.getSize());
        System.out.println("location of each node are follows: ");
        consistentHash.testBalance();
    }

}