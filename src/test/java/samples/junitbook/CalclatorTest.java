package samples.junitbook;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class CalclatorTest {

    public Calclator sut;

    @Before
    public void setUp() {
        sut = new Calclator();
    }

    @Test
    public void 割り算は普通に出来ること() {
        // act & assert
        assertThat(sut.divide(12, 2), is(6));
    }

    @Test(expected = IllegalArgumentException.class)
    public void 皆が普通に思いつくようなゼロ割はNG() {
        sut.divide(12, 0);
    }

}
