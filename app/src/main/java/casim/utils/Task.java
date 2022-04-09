package casim.utils;

public interface Task<E extends Exception> {
    void execute() throws E;
}
