package dk.university.adm.infrastructure;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.enterprise.util.Nonbinding;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Configurable {
    //Nonbinding means that the attribute should not be included when qualifiers are compared
	@Nonbinding
    String key() default "";

    @Nonbinding
    String defaultValue() default "";
}
