package di.annotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import di.Book;
import di.MyBook;

public class AnnotationMain {
	public static void main(String[] args) throws
		InvocationTargetException,
		NoSuchMethodException,
		InstantiationException,
		IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
		// getAnnotation();
		// getAnnotationAtFields();
		// createInstanceAndUseFields();
		createInstanceAndUseMethods();
	}

	/* annotation 정보를 가져오는 법 */
	public static void getAnnotation() {
		Arrays.stream(Book.class.getDeclaredAnnotations()).forEach(System.out::println);

		// 얘도 field, method 가져올 때랑 비슷함 getDeclaredAnnotations() 쓰면 해당 클래스에 붙은 annotation만 가져옴
		Arrays.stream(MyBook.class.getAnnotations()).forEach(System.out::println);
	}

	/* 위를 활용해서 field에 붙은 annotation정보를 가져오기 */
	public static void getAnnotationAtFields() {
		Arrays.stream(Book.class.getDeclaredFields()).forEach(f -> {
			Arrays.stream(f.getAnnotations()).forEach(a -> {
				if (a instanceof MyAnnotation myAnnotation) {
					System.out.println(myAnnotation.value());
					System.out.println(myAnnotation.number());
				}
			});
		});
	}

	/* Class 객체로 부터 인스턴스를 생성하고 해당 인스턴스의 필드에 reflection 을 이용하여 접근하기 ( private 필드도 ) */
	public static void createInstanceAndUseFields() throws NoSuchMethodException, InvocationTargetException,
		InstantiationException, IllegalAccessException, NoSuchFieldException {
		Class<Book> bookClass = Book.class;
		Constructor<Book> constructor = bookClass.getConstructor(String.class);
		Book book = constructor.newInstance("myBookParam");


		// static한 field이므로 특정한 Object를 넘겨주지 않아도 됨.
		Field a = Book.class.getDeclaredField("A");
		System.out.println(a.get(null));
		a.set(null, "new_value!");
		System.out.println(a.get(null));

		// 특정 인스턴스에 해당하는 필드를 가져올 때
		Field b = Book.class.getDeclaredField("B");
		b.setAccessible(true);
		System.out.println(b.get(book));
		b.set(book, "new_B!");
		System.out.println(b.get(book));
	}

	/* Class 객체로 부터 인스턴스를 생성하고 해당 인스턴스의 메서드를 이용하기 ( private 메서드도 ) */
	public static void createInstanceAndUseMethods() throws
		ClassNotFoundException,
		NoSuchMethodException,
		InvocationTargetException,
		InstantiationException,
		IllegalAccessException
	{
		Class<?> bookClass = Class.forName("di.Book");
		Constructor<?> constructor = bookClass.getConstructor(null);
		Book book = (Book)constructor.newInstance();
		System.out.println(book);

		//static 메서드를 실행할 때
		Method d = Book.class.getDeclaredMethod("d");
		d.invoke(book);

		// 특정 인스턴스에 속한 메서드를 실행할 때 (파라미터 X)
		Method c = Book.class.getDeclaredMethod("c");
		c.setAccessible(true);
		c.invoke(book);

		// 특정 인스턴스에 속한 메서드를 실행할 때 (파라미터 O)
		Method sum = Book.class.getDeclaredMethod("sum", int.class, int.class);
		int invoke = (int) sum.invoke(book, 5, 3);
		System.out.println(invoke);

	}
}
