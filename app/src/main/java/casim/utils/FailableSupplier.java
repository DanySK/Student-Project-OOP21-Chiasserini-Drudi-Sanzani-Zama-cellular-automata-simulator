package casim.utils;

public interface FailableSupplier<T, E extends Exception> {
    T run() throws E;
}
