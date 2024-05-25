package di;

import di.annotation.MyAnnotation;

@MyAnnotation(value = "paul!", number = 80)
public class Book {
	public static String A = "VALUE_A";
	private String B = "VALUE_B";

	public Book() {
	}

	public Book(String b) {
		B = b;
	}

	private void c(){
		System.out.println("C");
	}
	public static void d(){
		System.out.println("D");
	}

	public int sum(int left, int right) {
		return left + right;
	}
}
