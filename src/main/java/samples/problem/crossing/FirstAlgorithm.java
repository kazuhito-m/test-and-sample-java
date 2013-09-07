package samples.problem.crossing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FirstAlgorithm implements CrossPointCounter {

	public long countCrossPoint(File target) throws IOException {
		// 交点カウント
		long crossCount = 0;

		int from = 0;
		int to = 0;

		int toMin = Integer.MIN_VALUE;
		int toMax = Integer.MAX_VALUE;

		int[] pos = new int[320000];

		// ファイルの一行ずつ読み込み。
		FileReader fr = new FileReader(target);
		BufferedReader br = new BufferedReader(fr);
		String line = null;
		while ((line = br.readLine()) != null) {

			from++;
			to = Integer.parseInt(line);
			pos[from] = to;
			
			if (from % 10000 == 0) {
				System.out.println("now line : " + from);
			}
			
			// 前の線と重なる可能性が皆無なら
			if (from < to && to > toMax) {
				continue;
			}

			for (int testFrom = 1; testFrom < from; testFrom++) {
				int testTo = pos[testFrom];
				if ((from < testFrom && to > testTo) || (from > testFrom && to < testTo)) {
					crossCount++;
				}
			}
			
			if (to < toMin) {
				toMin = to;
			} else {
				toMax = to;
			}
			
		}
		br.close();
		fr.close();

		return crossCount;
	}
}
