package byteCode;

import static net.bytebuddy.matcher.ElementMatchers.*;

import java.io.File;
import java.io.IOException;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

public class Magician {
	public static void main(String[] args) {
		// 아래처럼 클래스파일을 읽어서 조작할 수 있음.
/*		try {
			new ByteBuddy().redefine(Hat.class)
				.method(named("pullOut")).intercept(FixedValue.value("Rabbit!"))
				.make().saveIn(new File("C:\\baekgiseon\\java-jvm\\target\\classes\\"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		*/
		System.out.println(new Hat().pullOut());
	}
}
