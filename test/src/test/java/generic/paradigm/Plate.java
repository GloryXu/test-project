package generic.paradigm;

class Fruit {}
class Apple extends Fruit {}

public class Plate<T> {
    private T item;
    public Plate(T t) {
        item = t;
    }

    public void set (T t) {
        item = t;
    }

    public T get() {
        return item;
    }


    public static void main(String[] args) {
        // 上界
        Plate<? extends Fruit> p = new Plate<Apple>(new Apple());

        // 不能存入任何元素
//        p.set(new Fruit());
//        p.set(new Apple());

        // 读取出来的东西只能存放在Fruite或它的基类里
        Fruit newFruit1 = p.get();
        Object newFruit2 = p.get();
//        Apple newFruit3 = p.get();// error



        //--------------------------------
        // 下界
        Plate<? super Fruit> p1 = new Plate<Fruit>(new Fruit());
//        Plate<? super Fruit> p2 = new Plate<Object>(new Apple());

        // 存入元素正常
        p1.set(new Fruit());
        p1.set(new Apple());

        // 读取出来的东西只能存放在Object类中
//        Apple newFruit3 = p1.get();
//        Fruit newFruit4 = p1.get();
        Object newFruit5 = p1.get();

    }
}
