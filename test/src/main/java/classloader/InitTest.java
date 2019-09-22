package classloader;

import classloader.entity.Cat;
import org.junit.Test;

public class InitTest {

    @Test
    public void main() {
        Cat cat = new Cat("kitty", 2);
        System.out.println(cat);
        Cat cat2 = new Cat("Jerfield", 3);
        System.out.println(cat2);
    }
}
