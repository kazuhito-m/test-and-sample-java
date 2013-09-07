package samples.problem.crossing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThirdAlgorithm implements CrossPointCounter {

	class PointPair {
		public int from;
		public int to;
		public boolean isUpper = false;
		public boolean isChecked = false;
	}

	public long countCrossPoint(File target) throws IOException {
		// 交点カウント
		long crossCount = 0;

		List<PointPair> checkAll = new ArrayList<>();

		// ファイルの一行ずつ読み込み。
		FileReader fr = new FileReader(target);
		BufferedReader br = new BufferedReader(fr);
		String line = null;
		int i = 0;
		while ((line = br.readLine()) != null) {
			i++;
			int from = i;
			int to = Integer.parseInt(line);

			PointPair pair = new PointPair();
			pair.from = from;
			pair.to = to;
			// 上りと下りに仕分ける。
			pair.isUpper = (from <= to);

			// 全体に足す。
			checkAll.add(pair);

		}
		br.close();
		fr.close();

		System.out.println("全数" + checkAll.size());

		// ポイント配列に移植
		PointPair[] all = new PointPair[checkAll.size() + 1];
		int f = 1;
		for (PointPair p : checkAll) {
			all[f++] = p;
		}

		// 計測
		Date start = new Date();

		int tgtFrom2 = -1;
		for (PointPair pair : all) {
			tgtFrom2++;
			if (pair == null) {
				System.out.println(tgtFrom2);
				continue;
			}
			if (pair.from >= 300000) {
				crossCount++;
			}
		}
		System.out.println((new Date().getTime()) - start.getTime() + "ミリ秒");

		start = new Date();
		for (int from = 1; from < all.length; from++) {
			PointPair p = all[from];
			if (!p.isChecked)
				continue;
			if (from >= 1) {
				crossCount++;
			}
		}
		System.out.println((new Date().getTime()) - start.getTime() + "ミリ秒");

		if (true)
			return 0;

		// 保存終了。こっから、本当に数えていく。
		int from = 0;
		boolean isUpper = false;

		// 全件ループ開始。
		for (i = 1; i <= all.length; i++) {
			PointPair pair = all[i];
			from++;
			pair.isChecked = true;

			// debug
			if (pair.from % 1000 == 0) {
				System.out.println("now line: " + from);
			}

			// 比較処理開始
			for (int j = 1; j <= all.length; j++) {
				PointPair bPair = all[j];

				if (pair.isUpper) {
					if (bPair.isUpper) {
						// 上り同士は、排除出来ないので、全回し
						if (pair.from < bPair.from && pair.to > bPair.to) {
							crossCount++;
						}
					} else {
						// 上りと下りなら、fromが下回ってるものは相手しなくてよい。
						if (pair.from < bPair.from) {
							if (pair.to > bPair.to) {
								crossCount++;
							}
						}
					}
				} else {
					// 下りの場合。
					if (bPair.isUpper) {
						// 下りと上りなら、fromが上回ってる場合は、相手しなくてよい。
						if (pair.from > bPair.from) {
							if (pair.to < bPair.to) {
								crossCount++;
							}
						}
					} else {
						// 下り同士は、下方向への追い抜きの可能性が残るので。全部回し。
						if (pair.from > bPair.from && pair.to < bPair.to) {
							crossCount++;
						}
					}
				}
			}
		}
		return crossCount;
	}
}
