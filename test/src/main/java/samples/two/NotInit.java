package samples.two;

/**
 * 因为是数组new，而类没有被new，所以没有触发任何“主动引用”条款，属于“被动引用”
 */
public class NotInit {
    public static void main(String[] args) {
        SuperClass[] test = new SuperClass[10];
        System.out.println("nothing!");
    }
}
