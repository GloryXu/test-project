package queue;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueueDemo {

    public static void main(String[] args) {
        Queue<String> queue = new ArrayDeque<>(2);
        System.out.println("---" + queue);
        queue.offer("aaa");
        queue.offer("bbb");
        queue.offer("ccc");
        System.out.println("---" + queue);
        queue.add("ddd");
        System.out.println("---" + queue);
        System.out.println(queue.peek());
        System.out.println("after peek " + queue);
        System.out.println(queue.poll());
        System.out.println("after poll " + queue);
    }
}
