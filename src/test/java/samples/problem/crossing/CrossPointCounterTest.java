package samples.problem.crossing;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class CrossPointCounterTest {

	public CrossPointCounter sut;

	@Before
	public void setUp() {
		sut = new FirstAlgorithm();
	}

	@Ignore
	@Test
	public void サンプルファイルでの交点カウントが成功し9となること() throws IOException {
		// arrange
		File target = new File(
				"./target/test-classes/samples/problem/crossing/sample.txt");
		// act
		long actual = sut.countCrossPoint(target);
		// act & assert
		assertThat(actual, is(9L));
	}

	@Test
	public void 本番ファイル32万行くらいのやつで常識的時間に計測が終了すること() throws IOException {
		// arrange
		File target = new File(
				"./target/test-classes/samples/problem/crossing/crossing.txt");
		Date start = new Date();
		
		// act
		long actual = sut.countCrossPoint(target);
		
		// act & assert
		System.out.println((new Date().getTime()) - start.getTime() + "ミリ秒");
		assertThat(actual, is(20566716394L));
	}
	
}
