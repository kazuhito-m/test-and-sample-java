package samples.problem.crossing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SecondAlgorithm implements CrossPointCounter {

	public long countCrossPoint(File target) throws IOException {
		// 交点カウント
		long crossCount = 0;

		Map<Long, Long> up = new HashMap<Long, Long>();
		Map<Long, Long> down = new HashMap<Long, Long>();
		List<Long> all = new ArrayList<>();
		all.add(null);

		// ファイルの一行ずつ読み込み。
		FileReader fr = new FileReader(target);
		BufferedReader br = new BufferedReader(fr);
		String line = null;
		long i = 0;
		while ((line = br.readLine()) != null) {
			i++;
			long from = i;
			long to = Long.parseLong(line);

			// 全体に足す。
			all.add(to);

			// 上りと下りに仕分ける。
			if (from <= to) {
				up.put(from, to);
			} else {
				down.put(from, to);
			}
		}
		br.close();
		fr.close();

		// 保存終了。こっから、本当に数えていく。
		Set<String> crossPs = new HashSet<>();
		long from = 0;
		long tgtFrom = 0;
		long tgtTo = 0;
		boolean isUpper = false;

		// 全件ループ開始。
		for (Long k : all) {
			if (k == null)
				continue;
			from++;
			long to = k;
			
			// debug
			if (from % 1000 == 0) {
				System.out.println("now line: " + from);
			}

			// まず、辞書側から殺す
			if (isUpper = (from <= to)) {
				up.remove(from);
			} else {
				down.remove(from);
			}

			// 上りと下りで処理をわけ、必要でない部分は削る
			if (isUpper) {
				// 上りの場合。
				// 上り同士は、排除出来ないので、全回し
				for (Map.Entry<Long, Long> pair : up.entrySet()) {
					tgtFrom = pair.getKey();
					tgtTo = pair.getValue();

					if (from < tgtFrom && to > tgtTo) {
						crossCount++;
					}
				}

				// 上りと下りなら、fromが下回ってるものは相手しなくてよい。
				for (tgtFrom = from + 1; tgtFrom <= all.size(); tgtFrom++) {
					Long hitTest = down.get(tgtFrom);
					if (hitTest == null) {
						continue;
					}
					tgtTo = hitTest;

					if (to > tgtTo) {
						crossCount++;
					}
				}
			} else {
				// 下りの場合。
				// 下り同士は、下方向への追い抜きの可能性が残るので。全部回し。
				for (Map.Entry<Long, Long> pair : up.entrySet()) {
					tgtFrom = pair.getKey();
					tgtTo = pair.getValue();

					if (from > tgtFrom && to < tgtTo) {
						crossCount++;
					}
				}
				// 下りと上りなら、fromが上回ってる場合は、相手しなくてよい。
				for (tgtFrom = 1; tgtFrom < from; tgtFrom++) {

					Long hitTest = down.get(tgtFrom);
					if (hitTest == null) {
						continue;
					}

					tgtTo = hitTest;

					if (to < tgtTo) {
						crossCount++;
					}
				}
			}
		}
		return crossCount;
	}
}
