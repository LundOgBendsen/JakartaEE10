package dk.lundogbendsen.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import jakarta.inject.Qualifier;

@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface Danish {

}
