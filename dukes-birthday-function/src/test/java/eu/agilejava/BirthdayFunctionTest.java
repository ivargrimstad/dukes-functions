package eu.agilejava;

import com.fnproject.fn.testing.*;
import eu.agilejava.BirthdayFunction;
import org.junit.*;

import static org.junit.Assert.*;

public class BirthdayFunctionTest {

    @Rule
    public final FnTestingRule testing = FnTestingRule.createDefault();

    @Test
    public void shouldReturnGreeting() {
        testing.givenEvent().enqueue();
        testing.thenRun(BirthdayFunction.class, "handleRequest");

        FnResult result = testing.getOnlyResult();
        assertEquals("Hello, world!", result.getBodyAsString());
    }

}