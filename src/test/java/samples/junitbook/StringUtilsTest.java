package samples.junitbook;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class StringUtilsTest {

    public StringUtils sut;

    @Before
    public void setUp() {
        sut = new StringUtils();
    }

    @Test
    public void 小文字全部は小文字のまま変換しない() {
        // act & assert
        assertThat(sut.toSnakeCase("aaa"), is("aaa"));
    }

    @Test
    public void 引数HelloWorldを入力するとhello_worldが取得できる() {
        // act & assert
        assertThat(sut.toSnakeCase("HelloWorld"), is("hello_world"));
    }

    @Test
    public void 引数practiceJunitを入力するとpractice_junitが取得できる() {
        // act & assert
        assertThat(sut.toSnakeCase("practiceJunit"), is("practice_junit"));
    }

    @Test
    public void そもそもキャメルケースになってなかったらどう動く() {
        // act & assert
        assertThat(sut.toSnakeCase("CamelCase"), is("camel_case"));
    }

    @Test
    public void 全部大文字なら() {
        // act & assert
        assertThat(sut.toSnakeCase("CAMELCASE"), is("camelcase"));
    }

    @Test
    public void 最初からスネークケースなら() {
        // act & assert
        assertThat(sut.toSnakeCase("camel_case"), is("camel_case"));
    }

    @Test
    public void よう使われるテーブル名風のやつを放り込むと() {
        // act & assert
        assertThat(sut.toSnakeCase("TABLE_NAME"), is("table_name"));
    }
}
