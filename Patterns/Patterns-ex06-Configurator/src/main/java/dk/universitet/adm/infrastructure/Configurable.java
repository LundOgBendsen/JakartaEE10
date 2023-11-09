package dk.universitet.adm.infrastructure;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.enterprise.util.Nonbinding;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Configurable {
	//angiver at attributten ikke skal medtaget når qualifiers sammenlignes  
	@Nonbinding
    String key() default "";

    @Nonbinding
    String defaultValue() default "";
}
