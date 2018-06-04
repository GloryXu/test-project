package inner.clazz;

/**
 * 本地类
 *
 * （和内部类很像）
 * 1.只能访问外部类的静态属性
 * 2.不能声明静态变量和静态方法，但是能声明常量static final
 * 3.只能访问声明为final的本地变量
 *
 */
public class LocalClassExample {
    static String regularExpression = "[^0-9]";

    public static void validatePhoneNumber(String phoneNumber1, String phoneNumber2) {
        final int numberLength = 10;
        // Valid in JDK 8 and later
//         int numberLength = 10;// jdk 1.8编译时默认增加final关键字 1.7则编译不通过
        class PhoneNumber {
            String formattedPhoneNumber = null;
            public static final String farewell = "Bye bye";
            PhoneNumber(String phoneNumber) {
                // numberLength = 7;
                String currentNumber = phoneNumber.replaceAll(regularExpression, "");
                if (currentNumber.length() == numberLength)
                    formattedPhoneNumber = currentNumber;
                else
                    formattedPhoneNumber = null;
            }

            public String getNumber() {
                return formattedPhoneNumber;
            }
            // Valid in JDK 8 and later:
            public void printOriginalNumbers() {
                System.out.println("Original numbers are " + phoneNumber1 +
                    " and " + phoneNumber2);
            }
        }
        PhoneNumber myNumber1 = new PhoneNumber(phoneNumber1);
        PhoneNumber myNumber2 = new PhoneNumber(phoneNumber2);
        // Valid in JDK 8 and later:
//        myNumber1.printOriginalNumbers();
        if (myNumber1.getNumber() == null)
            System.out.println("First number is invalid");
        else
            System.out.println("First number is " + myNumber1.getNumber());
        if (myNumber2.getNumber() == null)
            System.out.println("Second number is invalid");
        else
            System.out.println("Second number is " + myNumber2.getNumber());
    }

    public static void main(String... args) {
        validatePhoneNumber("123-456-7890", "456-7890");
    }
}
