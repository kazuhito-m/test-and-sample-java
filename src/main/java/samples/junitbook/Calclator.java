package samples.junitbook;

/** 計算をよろず司る暮らす。
 * @author kazuhito_m */
public class Calclator {

    /** 割り算する。
     * @param src
     * @param dst
     * @return 割り算した結果を返す。
     * @throws IllegalAccessException */
    public int divide(int src, int dst) {
        if (dst == 0) {
            throw new IllegalArgumentException();
        }
        return src / dst;
    }
}
