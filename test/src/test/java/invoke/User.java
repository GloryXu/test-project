package invoke;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class User {

    public static final String STATIC = "STATIC_xugr";

    public String name;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Integer age;

    public void info() {
        System.out.printf("name is %s, age is %d", this.name, this.age);
    }

    public static void staticMethod() {
        System.out.println("staticMethod");
    }
}
