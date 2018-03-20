package reflect;

import java.lang.reflect.Modifier;

import org.junit.Test;

public class ReflectTest {

	@Test
	public void testA(){
		int a = 100;
		int b = 200;
		a = a ^ b;
        System.out.println("a = " + a + ", b = " + b);
		b = a ^ b;
        System.out.println("a = " + a + ", b = " + b);
		a = a ^ b;
		System.out.println("a = " + a + ", b = " + b);
	}
	
	@Test
	public void testModifier(){
		System.out.println(Modifier.FINAL);
		System.out.println(Modifier.isFinal(String.class.getModifiers()));
		System.out.println(Modifier.isFinal(Integer.class.getModifiers()));
		System.out.println(Modifier.isInterface(Integer.class.getModifiers()));
	}
	
	@Test
	public void testDataTransform(){
		int a = 1073741824;
		System.out.println(Integer.toHexString(a));
		a = 48;
		System.out.println(Integer.toHexString(a));
//		System.out.println(Integer.parseInt("01000000000000000000000000000000",2));
//		System.out.println(Integer.numberOfLeadingZeros(a));
	}
}
