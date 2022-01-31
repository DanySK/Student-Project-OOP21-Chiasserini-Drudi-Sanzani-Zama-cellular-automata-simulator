package casim.utils;

import java.util.Optional;

/**
 * A result that allow to encapsulate return values of functions that may throw an exception.
 *
 * The original idea for the implementation and the use of this class was taken here:
 *   https://github.com/Zeegomo/nwoolcan/blob/master/src/main/java/nwoolcan/utils/Result.java
 *
 * @param <T> Result type.
 */
public final class Result<T> {
    private final Optional<T> value;
    private final Optional<Exception> exception; 

    private Result(final Optional<T> value, final Optional<Exception> exception) {
        this.value = value;
        this.exception = exception;
    }

    /** 
     * Create a {@link Result} that contains value.
     * 
     * @param <T> The type of value.
     * @param value the value to be encapsulated.
     * @return {@link Result} containing value.
     */
    public static <T> Result<T> of(final T value) {
        return new Result<>(Optional.of(value), Optional.empty());
    }

    /** 
     * Create a {@link Result} that contains exception.
     * 
     * @param <T> The type of the value to be returned.
     * @param exception the exception to be ecapsulated.
     * @return {@link Result} containing exception.
     */
    public static <T> Result<T> error(final Exception exception) {
        return new Result<>(Optional.empty(), Optional.of(exception));
    }

    /** 
     * Return a {@link Result} containing {@link Empty}.
     * 
     * @return An empty {@link Result}.
     */
    public static Result<Empty> ofEmpty() {
        return Result.of(new Empty() { });
    }

    /** 
     * Return true if {@link Result} contains a value.
     * 
     * @return whether the {@link Result} contains a value.
     */
    public boolean isPresent() {
        return this.value.isPresent();
    }

    /** 
     * Return true if {@link Result} contains an exception.
     * 
     * @return whether the {@link Result} contains an exception.
     */
    public boolean isError() {
        return this.exception.isPresent();
    }

    /** 
     * Return the value contained in {@link Result} if any, otherwise throws a {@link NoSuchElementException}.
     * 
     * @return the value contained in {@link Result}.
     */
    public T getValue() {
        return this.value.get();
    }

    /** 
     * Return the exception contained in {@link Result} if any, otherwise throws a {@link NoSuchElementException}.
     * 
     * @return the exception contained in {@link Result}
     */
    public Exception getError() {
        return this.exception.get();
    }

    /** 
     * Return a simple string representation of the value/exception that {@link Result} contains.
     * 
     * @return string representation of {@link Result} content.
     */
    @Override
    public String toString() {
        return this.isPresent() ? this.getValue().toString() : this.getError().toString();
    }

    /** 
     * Return true if some other object "obj" is equal to this {@link Result}.
     * "obj" is considered equal if:
     *  - it is a {@link Result}, and:
     *  - the value contained in "obj" compares equal to the value contained in "this" via equals().
     * 
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Result)) {
            return false;
        }

        final Result<?> other = (Result<?>) obj;

        return this.isPresent() 
            ? this.value.equals(other.value)
            : this.exception.equals(other.exception);
    }

    /** 
     * Return the hashcode of the present value, 0 if there is no value.
     * 
     * @return hash code of the contained value, 0 if there is no value.
     */
    @Override
    public int hashCode() {
        return this.isPresent() ? this.getValue().hashCode() : 0;
    }
}
