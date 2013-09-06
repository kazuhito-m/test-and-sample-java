package samples.problem.crossing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FirstAlgo implements CrossPointCounter {

	public long countCrossPoint(File target) throws IOException {
		// 交点カウント
		long crossCount = 0;

		int from = 0;
		int to = 0;

		int to_min = Integer.MIN_VALUE;
		int to_max = Integer.MAX_VALUE;

		int[] pos = new int[320000];

		// ファイルの一行ずつ読み込み。
		FileReader fr = new FileReader(target);
		BufferedReader br = new BufferedReader(fr);
		String line = null;
		while ((line = br.readLine()) != null) {

			from++;
			to = Integer.parseInt(line);
			pos[from] = to;
			
			if (from % 1000 == 0) {
				System.out.println("now line : " + from);
			}
			
			// 前の線と重なる可能性が皆無なら
			if (from < to && to > to_max) {
				continue;
			}

			for (int testFrom = 1; testFrom < from; testFrom++) {
				int testTo = pos[testFrom];
				if ((from < testFrom && to > testTo) || (from > testFrom && to < testTo)) {
					crossCount++;
				}
			}
			
			if (to < to_min) {
				to_min = to;
			} else {
				to_max = to;
			}
			
		}
		br.close();
		fr.close();

		return crossCount;
	}
}
