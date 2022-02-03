package casim.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

import com.google.common.base.Supplier;

/**
 * Test class for {@link Result}.
 */
public class ResultTest {

    private static final String TEXT = "TEXT";
    private static final UnsupportedOperationException EXCEPTION 
        = new UnsupportedOperationException("Test");

    /**
     * Test {@link Result#equals()} method.
     */
    @Test
    public void testEquals() {
        final String text1 = "Test1";
        final String text2 = "Test2";
        final Exception ex1 = new IllegalArgumentException();
        final Exception ex2 = new Exception();
        final var rText1 = Result.of(text1);
        final var rText2 = Result.of(text1);
        final var rText3 = Result.of(text2);
        final var rEx1 = Result.error(ex1);
        final var rEx2 = Result.error(ex1);
        final var rEx3 = Result.error(ex2);
        final var rEmpty = Result.ofEmpty();
        assertEquals(rText1, rText1);
        assertEquals(rText1, rText2);
        assertNotEquals(rText1, rText3);
        assertEquals(rEx1, rEx2);
        assertNotEquals(rEx1, rEx3);
        assertNotEquals(rText1, rEmpty);
        assertNotEquals(rEx1, rEmpty);
    }

    /**
     * Test {@link Result#error()} method.
     */
    @Test
    public void testError() {
        final var r = Result.error(EXCEPTION);
        assertEquals(EXCEPTION, r.getError());
        assertTrue(r::isError);
        assertFalse(r::isPresent);
    }

    /**
     * Test {@link Result#flatMap()} method.
     */
    @Test
    public void testFlatMap() {
        final var value = 1;
        final Function<Integer, Result<Integer>> function = (x) -> Result.of(x * 2);
        final var r1 = Result.of(value);
        final var r2 = Result.<Integer>error(EXCEPTION);
        final var mapped1 = r1.flatMap(function);
        final var mapped2 = r2.flatMap(function);
        assertTrue(mapped1::isPresent);
        assertTrue(mapped2::isError);
        assertEquals(function.apply(value), mapped1);
        assertEquals(r2, mapped2);
    }

    /**
     * Test {@link Result#flatMap()} method.
     */
    @Test
    public void testFlatMapSupplier() {
        final var value = 1;
        final Supplier<Result<String>> supplier = () -> Result.of(TEXT);
        final var r1 = Result.of(value);
        final var r2 = Result.<Integer>error(EXCEPTION);
        final var mapped1 = r1.flatMap(supplier);
        final var mapped2 = r2.flatMap(supplier);
        assertTrue(mapped1::isPresent);
        assertTrue(mapped2::isError);
        assertEquals(supplier.get(), mapped1);
        assertEquals(r2, mapped2);
    }

    /**
     * Test {@link Result#getError()} method.
     */
    @Test
    public void testGetError() {
        final var r1 = Result.of(TEXT);
        final var r2 = Result.error(EXCEPTION);
        assertThrows(NoSuchElementException.class, r1::getError);
        assertEquals(EXCEPTION, r2.getError());
    }

    /**
     * Test {@link Result#getValue()} method.
     */
    @Test
    public void testGetValue() {
        final var r1 = Result.of(TEXT);
        final var r2 = Result.error(EXCEPTION);
        assertEquals(TEXT, r1.getValue());
        assertThrows(NoSuchElementException.class, r2::getValue);
    }

    /**
     * Test {@link Result#hashCode()} method.
     */
    @Test
    public void testHashCode() {
        final var r1 = Result.of(TEXT);
        final var r2 = Result.error(EXCEPTION);
        assertEquals(TEXT.hashCode(), r1.hashCode());
        assertEquals(0, r2.hashCode());
    }

    /**
     * Test {@link Result#isError()} method.
     */
    @Test
    public void testIsError() {
        final var r1 = Result.of(1);
        final var r2 = Result.error(EXCEPTION);
        final var r3 = Result.ofEmpty();
        assertFalse(r1::isError);
        assertTrue(r2::isError);
        assertFalse(r3::isError);
    }

    /**
     * Test {@link Result#isPresent()} method.
     */
    @Test
    public void testIsPresent() {
        final var r1 = Result.of(TEXT);
        final var r2 = Result.error(EXCEPTION);
        final var r3 = Result.ofEmpty();
        assertTrue(r1::isPresent);
        assertFalse(r2::isPresent);
        assertTrue(r3::isPresent);
    }

    /**
     * Test {@link Result#map()} method.
     */
    @Test
    public void testMap() {
        final var value = 1;
        final Function<Integer, Integer> function = (x) -> x * 2;
        final var r1 = Result.of(value);
        final var r2 = Result.<Integer>error(EXCEPTION);
        final var mapped1 = r1.map(function);
        final var mapped2 = r2.map(function);
        assertTrue(mapped1::isPresent);
        assertTrue(mapped2::isError);
        assertEquals(function.apply(value), mapped1.getValue());
        assertEquals(r2, mapped2);
    }

    /**
     * Test {@link Result#mapError()} method.
     */
    @Test
    public void testMapError() {
        final String newErrorMsg = "NewErrorMsg";
        final Function<Exception, UnsupportedOperationException> function
             = x -> new UnsupportedOperationException(newErrorMsg);
        final var r1 = Result.error(EXCEPTION);
        final var r2 = Result.of(TEXT);
        final var mapped1 = r1.mapError(function);
        final var mapped2 = r2.mapError(function);
        assertTrue(mapped1::isError);
        assertTrue(mapped2::isPresent);
        assertEquals(function.apply(EXCEPTION).getMessage(), mapped1.getError().getMessage());
        assertEquals(r2, mapped2);
    }

    /**
     * Test {@link Result#of()} method.
     */
    @Test
    public void testOf() {
        final var r = Result.of(TEXT);
        assertTrue(r::isPresent);
        assertFalse(r::isError);
        assertEquals(TEXT, r.getValue());
    }

    /**
     * Test {@link Result#ofEmpty()} method.
     */
    @Test
    public void testOfEmpty() {
        final var r = Result.ofEmpty();
        assertTrue(r::isPresent);
        assertTrue(Arrays.asList(
            r.getValue()
            .getClass()
            .getInterfaces()
        ).contains(Empty.class));
    }

    /**
     * Test {@link Result#orElse()} method.
     */
    @Test
    public void testOrElse() {
        final var elseValue = "Else";
        final var r1 = Result.of(TEXT);
        final var r2 = Result.error(EXCEPTION);
        assertEquals(TEXT, r1.orElse(elseValue));
        assertEquals(elseValue, r2.orElse(elseValue));
    }

    /**
     * Test {@link Result#require()} method.
     */
    @Test
    public void testRequire() {
        final var r1 = Result.of(TEXT);
        final var r2 = Result.error(EXCEPTION);
        final var require1 = r1.require(str -> str.equals(TEXT));
        final var require2 = r1.require(str -> !str.equals(TEXT));
        final var require3 = r2.require(str -> str.equals(TEXT));
        assertEquals(r1, require1);
        assertEquals(r2, require3);
        assertNotEquals(r1, require2);
        assertTrue(require2::isError);
        assertEquals(IllegalArgumentException.class, require2.getError().getClass());
    }

    /**
     * Test {@link Result#require()} method.
     */
    @Test
    public void testRequireCustomException() {
        final var customEx = new IndexOutOfBoundsException();
        final var r1 = Result.of(TEXT);
        final var r2 = Result.error(EXCEPTION);
        final var require1 = r1.require(str -> str.equals(TEXT), customEx);
        final var require2 = r1.require(str -> !str.equals(TEXT), customEx);
        final var require3 = r2.require(str -> str.equals(TEXT), customEx);
        assertEquals(r1, require1);
        assertEquals(r2, require3);
        assertNotEquals(r1, require2);
        assertTrue(require2::isError);
        assertEquals(customEx.getClass(), require2.getError().getClass());
    }

    /**
     * Test {@link Result#require()} method.
     */
    @Test
    public void testRequireExceptionSupplier() {
        final Supplier<Exception> customEx = () -> new IndexOutOfBoundsException();
        final var r1 = Result.of(TEXT);
        final var r2 = Result.error(EXCEPTION);
        final var require1 = r1.require(str -> str.equals(TEXT), customEx);
        final var require2 = r1.require(str -> !str.equals(TEXT), customEx);
        final var require3 = r2.require(str -> str.equals(TEXT), customEx);
        assertEquals(r1, require1);
        assertEquals(r2, require3);
        assertNotEquals(r1, require2);
        assertTrue(require2::isError);
        assertEquals(customEx.get().getClass(), require2.getError().getClass());
    }

    /**
     * Test {@link Result#stream()} method.
     */
    @Test
    public void testStream() {
        final var r1 = Result.of(TEXT);
        assertEquals(1, r1.stream().count());
        assertEquals(TEXT, r1.stream().findFirst().get().getValue());
    }

    /**
     * Test {@link Result#toEmpty()} method.
     */
    @Test
    public void testToEmpty() {
        final var r1 = Result.of(TEXT);
        final var r2 = Result.error(EXCEPTION);
        final var toEmpty1 = r1.toEmpty();
        final var toEmpty2 = r2.toEmpty();
        assertEquals(r2, toEmpty2);
        assertTrue(toEmpty1::isPresent);
        assertFalse(toEmpty2::isPresent);
        assertFalse(toEmpty1::isError);
        assertTrue(toEmpty2::isError);
        assertNotEquals(r1.getValue(), toEmpty1.getValue());
        assertTrue(Arrays.asList(
            toEmpty1.getValue()
            .getClass()
            .getInterfaces()
        ).contains(Empty.class));
    }

    /**
     * Test {@link Result#toOptional()} method.
     */
    @Test
    public void testToOptional() {
        final var r1 = Result.of(TEXT);
        final var r2 = Result.error(EXCEPTION);
        assertEquals(Optional.of(TEXT), r1.toOptional());
        assertEquals(Optional.empty(), r2.toOptional());
    }

    /**
     * Test {@link Result#toString()} method.
     */
    @Test
    public void testToString() {
        final var r1 = Result.of(TEXT);
        final var r2 = Result.error(EXCEPTION);
        assertEquals(TEXT, r1.toString());
        assertEquals(EXCEPTION.toString(), r2.toString());
    }
}
