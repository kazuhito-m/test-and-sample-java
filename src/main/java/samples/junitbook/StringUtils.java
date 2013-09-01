package samples.junitbook;

public class StringUtils {

    /** キャメルケースをスネークケースに変える。
     * @param targetStr 対象文字列。
     * @return 変換された結果文字列。 */
    public static String toSnakeCase(String targetStr) {
        // ネットから拾ってきたコピペで事なきをえた。
        String convertedStr = targetStr.replaceAll("([A-Z]+)([A-Z][a-z])",
                "$1_$2").replaceAll("([a-z])([A-Z])", "$1_$2");
        return convertedStr.toLowerCase();
    }
}
