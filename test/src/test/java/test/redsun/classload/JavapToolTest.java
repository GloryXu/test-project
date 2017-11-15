package test.redsun.classload;

public class JavapToolTest {
	// 定义count实例变量，并为之指定初始值
	int count = 20;
	{
		// 初始化块中为count实例变量指定初始值
		count = 12;
	}
	// 定义两个构造器
	public JavapToolTest(){
		System.out.println(count);
	}
	public JavapToolTest(String name){
		System.out.println(name);
	}
}
