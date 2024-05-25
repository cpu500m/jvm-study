package di;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class DiMain {
	public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
		// getClassObject();
		// getFields();
		// getValues();
		// getMethods();
		// getSuperClass();
		// getInterface();
		useClassFields();
	}

	/* class 객체를 가져오는 방법 */
	public static void getClassObject() throws ClassNotFoundException {
		// 1. 타입을 통해 가져오기
		Class<Book> bookClass = Book.class;

		// 2. 인스턴스를 통해 가져오기
		Book book = new Book();
		Class<? extends Book> aClass = book.getClass();

		// 3. FQCN을 통해 가져오기
		Class<?> aClass1 = Class.forName("di.Book");
	}

	/* Class 객체의 Fields 꺼내는 메서드들 */
	public static void getFields() throws NoSuchFieldException {
		Class<Book> bookClass = Book.class;

		// getFields() : public 한 fields만 가져옴.
		Arrays.stream(bookClass.getFields()).forEach(System.out::println);

		// getDeclaredFields() : 해당 클래스 내 모든 필드 다가져옴.
		Arrays.stream(bookClass.getDeclaredFields()).forEach(System.out::println);

		// getDeclaredField() : 원하는 필드만 가져옴
		Field d = bookClass.getDeclaredField("d");

	}

	/* Class 객체를 이용해서 해당 클래스 타입인 객체의 필드 값 가져오기 */
	public static void getValues() {
		Class<Book> bookClass = Book.class;
		Book book = new Book();
		Arrays.stream(bookClass.getDeclaredFields()).forEach(f ->{
			try {
				f.setAccessible(true);
				System.out.printf("%s %s\n", f, f.get(book));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		});
	}

	/* Class 객체를 이용하여 메서드 가져오기 */
	public static void getMethods() {
		Class<Book> bookClass = Book.class;
		Book book = new Book();
		// 메서드 가져오기 . getMethods() 는 상속받은 메서드까지 출력 됨.
		Arrays.stream(bookClass.getDeclaredMethods()).forEach(System.out::println);
		// 생성자 가져오기. 얘도 비슷
		Arrays.stream(bookClass.getDeclaredConstructors()).forEach(System.out::println);
	}

	// 부모 클래스의 Class 객체 가져오기
	public static void getSuperClass(){
		Class<? super MyBook> superclass = MyBook.class.getSuperclass();
		System.out.println(superclass.getName());
	}

	// 구현한 인터페이스의 Class 객체 가져오기
	public static void getInterface() {
		Arrays.stream(MyBook.class.getInterfaces()).forEach(System.out::println);
	}

	// 아래 처럼 활용할 수 있음.
	public static void useClassFields() {
		Arrays.stream(Book.class.getDeclaredFields()).forEach(f->{
			int modifiers = f.getModifiers();
			System.out.println(f);
			System.out.println(Modifier.isPrivate(modifiers));
			System.out.println(Modifier.isStatic(modifiers));
		});
	}
}
