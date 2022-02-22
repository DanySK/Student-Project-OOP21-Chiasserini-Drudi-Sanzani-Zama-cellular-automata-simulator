package casim.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Abstract class that describes a Builder.
 * Not thread safe.
 */
public abstract class AbstractBuilder {
    private final Set<String> calledMethods = new HashSet<>();

    /**
     * Register the method that was called last.
     */
    protected void registerCall() {
        final var stackTrace = Thread.currentThread().getStackTrace();
        final var method = stackTrace[2].getMethodName();
        if (this.calledMethods.contains(method)) {
            throw new IllegalStateException(method + " has been called twice.");
        }
        this.calledMethods.add(method);
    }

    /**
     * Check a value with a {@link Predicate} and throws an exception if the test fails.
     * @param <T> The type of the value.
     * @param value The value that will be set.
     * @param predicate {@link Predicate} used to check the value.
     * @param errMsg Message displayed in case of failed test.
     */
    protected <T> void checkValue(final T value, final Predicate<T> predicate, final String errMsg) {
        if (!predicate.test(value)) {
            throw new IllegalArgumentException(errMsg);
        }
    }
}