package di.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.FIELD})
@Inherited
public @interface MyAnnotation {

	// value라고 필드명을 사용하면 MyAnnotation이라는 annotation 사용하는 곳에서 value = "내용" 이렇게 안줘도 됨
	// 그냥 @MyAnnotation("내용") 이렇게 쓰면 됨
	String value() default "paul";

	int number() default 100;
}
