package casim.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that tags a class as a playable automaton.
 * 
 * This will be used by the Menu Controller to find every automata to display.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PlayableAutomaton {
    String AutomatonName();
}
