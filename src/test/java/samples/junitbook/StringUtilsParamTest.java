package samples.junitbook;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class StringUtilsParamTest {

    @Parameterized.Parameters(name = "{index}: toSnake({0})={1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] { {"aaa", "aaa"}, {"A", "a"},
                {"abcDef", "abc_def"}, {"ABC", "abc"},
                {"HelloWorld", "hello_world"},
                {"practiceJunit", "practice_junit"}});
    }

    private String input;
    private String expected;

    public StringUtilsParamTest(String input, String expected) {
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void test() {
        assertThat(StringUtils.toSnakeCase(input), is(expected));
    }
}
