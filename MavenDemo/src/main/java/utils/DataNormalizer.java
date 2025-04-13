package utils;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
public class DataNormalizer {
    public static double[][] zScoreNormalize(double[][] data) {
        double[][] normalized = new double[data.length][data[0].length];
        for (int col = 0; col < data[0].length; col++) {
            double[] column = getColumn(data, col);
            double mean = new Mean().evaluate(column);
            double std = new StandardDeviation().evaluate(column);

            for (int row = 0; row < data.length; row++) {
                normalized[row][col] = (data[row][col] - mean) / std;
            }
        }
        return normalized;
    }

    private static double[] getColumn(double[][] data, int col) {
        double[] column = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            column[i] = data[i][col];
        }
        return column;
    }
}
