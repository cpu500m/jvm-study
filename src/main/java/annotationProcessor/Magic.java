package annotationProcessor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // interface ,class ,enum에 붙을 수 있음.
@Retention(RetentionPolicy.SOURCE)
public @interface Magic {

}
