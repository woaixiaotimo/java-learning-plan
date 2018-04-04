package test.annotationTest;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)//对注解的使用范围进行限定
@Retention(RUNTIME)//它所注解的注解保留多久
@Documented
public @interface FruitName {
    String value() default "";
}
